package az.edadi.back.service.impl;

import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.message.Message;
import az.edadi.back.entity.message.Thread;
import az.edadi.back.entity.message.UserThread;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.UserSummary;
import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import az.edadi.back.repository.MessageRepository;
import az.edadi.back.repository.UserThreadRepository;
import az.edadi.back.service.ChatService;
import az.edadi.back.utility.AuthUtil;
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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatServiceImpl implements ChatService {

    private final UserThreadRepository userThreadRepository;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;
    int MESSAGES_PAGE_SIZE = 15;

    @Async
    @Override
    public void saveMessage(MessageRequestModel messageRequestModel, Long currentUserID) throws JsonProcessingException {
        UserThread userThread = userThreadRepository.findByUserIdAndThreadId(currentUserID, messageRequestModel.getThreadId()).orElseThrow(
                () -> new EntityNotFoundException("Thread Not Found")
        );

        notifyUsers(currentUserID, userThread.getThread(), messageRequestModel);
        Message message = Message.builder()
                .date(new Date())
                .user(new User(currentUserID))
                .text(messageRequestModel.getContent())
                .thread(userThread.getThread())
                .build();
        messageRepository.save(message);
    }

    @Override
    public List<MessageResponseModel> getThreadMessages(Long threadId, int page) {
        UserThread userThread = userThreadRepository
                .findByUserIdAndThreadId(AuthUtil.getCurrentUserId(), threadId)
                .orElseThrow(UserAuthorizationException::new);

        PageRequest pageRequest = PageRequest.of(
                page,
                MESSAGES_PAGE_SIZE,
                Sort.by("date").descending()
        );

        return messageRepository.findByThreadId(threadId, pageRequest).stream().map(
                MessageResponseModel::new
        ).collect(Collectors.toList());
    }

    @Async
    void notifyUsers(Long currentUserId, Thread thread, MessageRequestModel messageRequestModel) {

        thread.getUserThread().forEach((userThread) ->
                {
                    try {
                        simpMessagingTemplate.convertAndSendToUser(
                                String.valueOf(userThread.getUser().getId()),
                                "/message",
                                objectMapper.writeValueAsString(MessageResponseModel.builder()
                                        .body(messageRequestModel.getContent())
                                        .date(new Date().toString())
                                        .incoming(!currentUserId.equals(userThread.getUser().getId()))
                                        .author(new UserSummary(userThread.getUser()))
                                        .threadId(messageRequestModel.getThreadId())
                                        .build())
                        );
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

}
