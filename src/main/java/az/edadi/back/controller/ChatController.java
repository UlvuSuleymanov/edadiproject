package az.edadi.back.controller;

import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import az.edadi.back.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/send/message")
//    @SendTo("/message")
    public MessageResponseModel messageResponseModel(
            @Payload MessageRequestModel messageRequestModel,
            StompHeaderAccessor accessor
    ) throws JsonProcessingException {

        chatService.saveMessage(
                messageRequestModel, Long.valueOf(accessor.getUser().getName())
        );
        return MessageResponseModel.builder()
                .body(messageRequestModel.getContent())
                .date(new Date().toString())
                .threadId(messageRequestModel.getThreadId())
                .build();
    }
}
