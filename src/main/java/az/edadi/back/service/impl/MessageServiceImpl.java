package az.edadi.back.service.impl;

import az.edadi.back.entity.User;
import az.edadi.back.entity.message.Message;
import az.edadi.back.entity.message.UserThread;
import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import az.edadi.back.repository.MessageRepository;
import az.edadi.back.repository.ThreadRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.repository.UserThreadRepository;
import az.edadi.back.service.MessageService;
import az.edadi.back.utility.AuthUtil;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserRepository userRepository;
    private final ThreadRepository threadRepository;
    private final UserThreadRepository userThreadRepository;
    private final MessageRepository messageRepository;

    public MessageServiceImpl(SimpMessagingTemplate simpMessagingTemplate,
                              UserRepository userRepository,
                              ThreadRepository threadRepository,
                              UserThreadRepository userThreadRepository, MessageRepository messageRepository) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.userRepository = userRepository;
        this.threadRepository = threadRepository;
        this.userThreadRepository = userThreadRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageResponseModel sendChatMessage(MessageRequestModel messageRequestModel) {
        List<UserThread> threadList = userThreadRepository.findByThreadId(messageRequestModel.getThreadId());
        User currentUser= userRepository.getById(AuthUtil.getCurrentUserId());
        if(threadList.size()<2 || checkUserInThread(threadList))
            throw new RuntimeException("User can't send message to this thread");

        Message message = new Message(messageRequestModel);
        message.setUser(currentUser);
        message.setThread(threadList.get(0).getThread());
        Message sendedMessage = messageRepository.save(message);
        MessageResponseModel messageResponseModel = new MessageResponseModel(sendedMessage);
        sendNotifications(threadList,messageResponseModel);
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

    void sendNotifications(List<UserThread> threads, MessageResponseModel message) {
        Long currentUserId = AuthUtil.getCurrentUserId();

        for (UserThread userThread : threads)
            if (!userThread.getUser().getId().equals(currentUserId))
                simpMessagingTemplate.convertAndSendToUser(
                        String.valueOf(userThread.getUser().getId()),
                        "/queue/messages",
                        message.toString());

    }
}
