package az.edadi.back.service.impl;

import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.message.Message;
import az.edadi.back.entity.message.UserThread;
import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import az.edadi.back.repository.MessageRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.repository.UserThreadRepository;
import az.edadi.back.service.MessageService;
import az.edadi.back.utility.AuthUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageServiceImpl implements MessageService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserRepository userRepository;
    private final UserThreadRepository userThreadRepository;
    private final MessageRepository messageRepository;

    @Override
    public MessageResponseModel sendChatMessage(MessageRequestModel messageRequestModel) throws JsonProcessingException {
        List<UserThread> threadList = userThreadRepository.findByThreadId(messageRequestModel.getThreadId());
        User currentUser = userRepository.getById(AuthUtil.getCurrentUserId());
        if (threadList.size() < 2 || checkUserInThread(threadList))
            throw new RuntimeException("User can't send message to this thread");

        Message message = new Message(messageRequestModel);
        message.setUser(currentUser);
        message.setThread(threadList.get(0).getThread());
        Message sendedMessage = messageRepository.save(message);
        MessageResponseModel messageResponseModel = new MessageResponseModel(sendedMessage);
        sendNotifications(threadList, messageResponseModel);
        return messageResponseModel;
    }

    @Override
    public List<MessageResponseModel> getMessages(Long rec) {
        return null;
    }

    boolean checkUserInThread(List<UserThread> threadList) {
        Long currentUser = AuthUtil.getCurrentUserId();
        for (UserThread userThread : threadList)
            if (userThread.getId().equals(currentUser))
                return true;

        return false;
    }
    @Async
    void sendNotifications(List<UserThread> threads, MessageResponseModel message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        for (UserThread userThread : threads) {
            message.setIncoming(!userThread.getUser().getId().equals(message.getAuthor().getId()));
            simpMessagingTemplate.convertAndSendToUser(
                    String.valueOf(userThread.getUser().getId()),
                    "/queue/messages",
                    mapper.writeValueAsString(message));
        }

    }
}
