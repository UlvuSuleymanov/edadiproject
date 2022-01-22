package az.edadi.back.controller;

import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import az.edadi.back.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("api/message/me")
    ResponseEntity getChatRoomMessages(){
        return ResponseEntity.noContent().build();
    }


    @PostMapping("api/message")
    public MessageResponseModel greeting(@RequestBody MessageRequestModel messageRequestModel) throws JsonProcessingException {
        return  messageService.sendChatMessage(messageRequestModel);
    }
}
