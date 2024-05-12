package az.edadi.back.service.impl;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.message.Conversation;
import az.edadi.back.entity.message.Message;
import az.edadi.back.entity.message.Thread;
import az.edadi.back.exception.model.EdadiEntityNotFoundException;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.UserSummary;
import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import az.edadi.back.repository.ConversationRepository;
import az.edadi.back.repository.MessageRepository;
import az.edadi.back.repository.ThreadRepository;
import az.edadi.back.service.ConversationService;
import az.edadi.back.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageServiceImpl implements MessageService {

    private final ConversationRepository conversationRepository;
    private final ThreadRepository threadRepository;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;
    int MESSAGES_PAGE_SIZE = 15;


    @Override
    public MessageResponseModel sendMessage(MessageRequestModel messageRequestModel, Long currentUserID) throws JsonProcessingException {

        Thread thread = threadRepository.findById(messageRequestModel.getThreadId())
                .orElseThrow(() -> new EdadiEntityNotFoundException(EntityType.CONVERSATION));
        if (!thread.getUser().getId().equals(currentUserID))
            throw new UserAuthorizationException();
        Message message = Message.builder()
                .text(messageRequestModel.getContent())
                .thread(thread)
                .conversation(thread.getConversation())
                .build();
        message = messageRepository.saveAndFlush(message);
        notifyUsers(currentUserID, message);
        updateConversationLastMessageDate(thread.getConversation().getId());
        return null;
    }

    @Override
    public List<MessageResponseModel> getMessages(Long conversationId, int page) {
        PageRequest pageRequest = PageRequest.of(
                page - 1,
                MESSAGES_PAGE_SIZE,
                Sort.by("dateCreated").descending()
        );
        return messageRepository.findByConversationId(conversationId, pageRequest)
                .stream()
                .map(MessageResponseModel::new)
                .toList();
    }

    @Async
    void notifyUsers(Long currentUserId, Message message) {
        message.getConversation().getThreadList().forEach((userThread) ->
                {
                    try {
                        simpMessagingTemplate.convertAndSendToUser(
                                String.valueOf(userThread.getUser().getId()),
                                "/message",
                                objectMapper.writeValueAsString(MessageResponseModel.builder()
                                        .body(message.getText())
                                        .id(message.getId())
                                        .date(new Date().toString())
                                        .incoming(!currentUserId.equals(userThread.getUser().getId()))
                                        .author(new UserSummary(userThread.getUser()))
                                        .conversationId(message.getConversation().getId())
                                        .build())
                        );
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                }
        );
    }

    @Async
    void updateConversationLastMessageDate(Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new EdadiEntityNotFoundException(EntityType.CONVERSATION));
        conversation.setLastMessageDate(new Date());
        conversationRepository.save(conversation);
    }

}
