package az.edadi.back.controller;

import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import az.edadi.back.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;


    @MessageMapping("/send/message")
    public void messageResponseModel(@Payload MessageRequestModel messageRequestModel,
                                     StompHeaderAccessor accessor) throws JsonProcessingException {
        messageService.sendMessage(messageRequestModel, Long.valueOf(accessor.getUser().getName()));
    }

    @GetMapping("api/v1/message")
    List<MessageResponseModel> getThreadMessages(@RequestParam Long threadId, @RequestParam int page) {
        return messageService.getMessages(threadId, page);
    }
}
