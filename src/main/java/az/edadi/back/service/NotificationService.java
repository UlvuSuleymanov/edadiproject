package az.edadi.back.service;

import az.edadi.back.entity.app.Notification;
import az.edadi.back.entity.auth.User;
import az.edadi.back.model.response.NotificationResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface NotificationService {

    void sendNotification(Notification notification, User user) throws JsonProcessingException;
    void sendNotification(Notification notification, List<User> user);

    List<NotificationResponseModel> getNotifications(int page);

}
