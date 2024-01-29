package az.edadi.back.listener;

import az.edadi.back.entity.auth.User;
import az.edadi.back.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;
    private final UserRepository userRepository;

//    @EventListener
//    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
//         Long currentUserId= Long.valueOf(Objects.requireNonNull(event.getUser()).getName());
//         User user = userRepository
//                 .findById(currentUserId)
//                 .orElseThrow(
//                         ()->new EntityNotFoundException("User not found")
//                 );
//         user.setLastSeen(new Date());
//         userRepository.save(user);
//
//    }

}
