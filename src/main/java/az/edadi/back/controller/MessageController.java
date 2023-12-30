package az.edadi.back.controller;

import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import az.edadi.back.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/send/message")
    public MessageResponseModel messageResponseModel(
            @Payload MessageRequestModel messageRequestModel,
            StompHeaderAccessor accessor
    ) throws JsonProcessingException {

        messageService.sendMessageToRoom(
                messageRequestModel, Long.valueOf(accessor.getUser().getName())
        );
        return MessageResponseModel.builder()
                .body(messageRequestModel.getContent())
                .date(new Date().toString())
                .threadId(messageRequestModel.getThreadId())
                .build();
    }

    @GetMapping("api/message")
    List<MessageResponseModel> getThreadMessages(@RequestParam Long threadId, @RequestParam int page) {
        List<MessageResponseModel> messages = messageService.getMessages(threadId, page);
        return messages;
    }
}
