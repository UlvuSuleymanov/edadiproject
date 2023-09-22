package az.edadi.back.controller;

import az.edadi.back.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notification")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity getNotifications() {

        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}
