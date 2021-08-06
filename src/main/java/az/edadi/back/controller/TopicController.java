package az.edadi.back.controller;

import az.edadi.back.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    ResponseEntity addTopic(String title){
     return ResponseEntity.ok(topicService.addTopic(title));
    }

    @GetMapping
    ResponseEntity getTopics(){
        return ResponseEntity.ok(topicService.getTopics());
    }
}
