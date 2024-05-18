package az.edadi.back.controller;

import az.edadi.back.model.request.ConversationReq;
import az.edadi.back.model.response.ConversationRes;
import az.edadi.back.service.ConversationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/conversation")
public class ConversationController {
    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }
    @PostMapping
    ConversationRes createConversation(@RequestBody ConversationReq conversationReq){
        return conversationService.getConversationByUsername(conversationReq);
    }
    @GetMapping("{id}")
    ConversationRes getConversationRes(@PathVariable Long id){
        return conversationService.getConversationById(id);
    }
    @GetMapping()
    List<ConversationRes> getConversationResList(@RequestParam(value = "page",defaultValue = "1") int page) throws InterruptedException {
       return    conversationService.getConversationList(page);
    }

}
