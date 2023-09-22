package az.edadi.back.service.impl;

import az.edadi.back.entity.app.Notification;
import az.edadi.back.entity.auth.User;
import az.edadi.back.model.response.NotificationResponseModel;
import az.edadi.back.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationServiceImpl implements NotificationService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void sendNotification(Notification notification, User user) throws JsonProcessingException {
        simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(user.getId()),
                "/notification",
                getJson(notification));
    }

    @Override
    public void sendNotification(Notification notification, List<User> users) {
        users.forEach((user -> {
            try {
                sendNotification(notification, user);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    @Override
    public List<NotificationResponseModel> getNotifications(int page) {

        return Collections.emptyList();
    }

    String getJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}
