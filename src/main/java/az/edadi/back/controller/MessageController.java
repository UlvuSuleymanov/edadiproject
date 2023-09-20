//package az.edadi.back.controller;
//
//import az.edadi.back.model.request.MessageRequestModel;
//import az.edadi.back.service.MessageService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.simp.user.SimpUserRegistry;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@RestController
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class MessageController {
//
////    private final MessageService messageService;
//    private final SimpUserRegistry simpUserRegistry;
//
//    @GetMapping("api/messages/current")
//    @PreAuthorize("hasAuthority('admin:read')")
//    public List<String> getCurrentSubscribers() throws JsonProcessingException {
//        List<String> ids = new ArrayList<>();
//         simpUserRegistry.getUsers().forEach(
//                simpUser -> ids.add(simpUser.getName())
//        );
//        return ids;
//    }
//
//    @GetMapping("api/message/me")
//    ResponseEntity getChatRoomMessages() {
//        return ResponseEntity.noContent().build();
//    }
//
//
//    @PostMapping("api/message")
//    public void sendMessage(@RequestBody MessageRequestModel messageRequestModel) throws JsonProcessingException {
//        messageService.sendChatMessage(messageRequestModel);
//    }
//}
