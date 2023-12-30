package az.edadi.back.service.impl;

import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.message.Message;
import az.edadi.back.entity.message.Thread;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import az.edadi.back.repository.MessageRepository;
import az.edadi.back.repository.RoomRepository;
import az.edadi.back.repository.ThreadRepository;
import az.edadi.back.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageServiceImpl implements MessageService {

    private final RoomRepository roomRepository;
    private final ThreadRepository threadRepository;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;
    int MESSAGES_PAGE_SIZE = 15;

//    @Async
//    @Override
//    public void saveMessage(MessageRequestModel messageRequestModel, Long currentUserID) throws JsonProcessingException {
//        UserThread userThread = userThreadRepository.findByUserIdAndThreadId(currentUserID, messageRequestModel.getThreadId()).orElseThrow(
//                () -> new EntityNotFoundException("Thread Not Found")
//        );

//    }

//    @Override
//    public List<MessageResponseModel> getRoomMessages(Long roomId, int page) {
//        messageRepository
//        Thread thread = threadRepository
//                .findByUserIdAndThreadId(AuthUtil.getCurrentUserId(), threadId)
//                .orElseThrow(UserAuthorizationException::new);
//
//        PageRequest pageRequest = PageRequest.of(
//                page,
//                MESSAGES_PAGE_SIZE,
//                Sort.by("date").descending()
//        );

//        return messageRepository.findByThreadId(1L, ).stream().map(
//                MessageResponseModel::new
//        ).collect(Collectors.toList());
//        return Collections.emptyList();
//    }


    @Override
    public MessageResponseModel sendMessageToRoom(MessageRequestModel messageRequestModel, Long currentUserID) throws JsonProcessingException {

        Optional<Thread> thread = threadRepository.findById(messageRequestModel.getThreadId());

        if (thread.isEmpty())
            throw new EntityNotFoundException();

        if (thread.get().getUser().getId().equals(currentUserID))
            throw new UserAuthorizationException();

//        Optional<Room> optionalRoom = threadRepository.getCommonThreads(Arrays.asList(currentUserID, messageRequestModel)).get().getRoom();
//        notifyUsers(currentUserID, userThread.getThread(), messageRequestModel);
        Message message = Message.builder()
                .date(new Date())
                .user(new User(currentUserID))
                .text(messageRequestModel.getContent())
                .thread(thread.get())
                .room(thread.get().getRoom())
                .build();
        messageRepository.save(message);
        return null;
    }

    @Override
    public List<MessageResponseModel> getMessages(Long roomId, int page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                MESSAGES_PAGE_SIZE,
                Sort.by("date").descending()
        );
        return messageRepository.findByRoomId(roomId,pageRequest)
                .stream()
                .map(MessageResponseModel::new)
                .toList();
    }

//    @Async
//    void notifyUsers(Long currentUserId, Thread thread, MessageRequestModel messageRequestModel) {
//
//        thread.getUserThread().forEach((userThread) ->
//                {
//                    try {
//                        simpMessagingTemplate.convertAndSendToUser(
//                                String.valueOf(userThread.getUser().getId()),
//                                "/message",
//                                objectMapper.writeValueAsString(MessageResponseModel.builder()
//                                        .body(messageRequestModel.getContent())
//                                        .date(new Date().toString())
//                                        .incoming(!currentUserId.equals(userThread.getUser().getId()))
//                                        .author(new UserSummary(userThread.getUser()))
//                                        .threadId(messageRequestModel.getThreadId())
//                                        .build())
//                        );
//                    } catch (JsonProcessingException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//        );
//    }

}
