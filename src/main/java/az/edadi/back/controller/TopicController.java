package az.edadi.back.controller;

import az.edadi.back.entity.Topic;
import az.edadi.back.model.SummaryModel;
import az.edadi.back.repository.TopicRepository;
import az.edadi.back.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    private final TopicService topicService;
    private final TopicRepository topicRepository;

    @Autowired
    public TopicController(TopicService topicService, TopicRepository topicRepository) {
        this.topicService = topicService;
        this.topicRepository = topicRepository;
    }

    @PostMapping
    ResponseEntity addTopic(@RequestBody String title){
     return ResponseEntity.ok(topicService.addTopic(title));
    }

    @GetMapping
    ResponseEntity getTopics(){
        return ResponseEntity.ok(topicService.getTopics());
    }

    @GetMapping("/{id}")
    ResponseEntity getTopic(@PathVariable Long id){
        Optional<Topic> topic= topicRepository.findById(id);
        if(topic.isPresent())
            return ResponseEntity.ok(new SummaryModel(topic.get().getId(),topic.get().getTitle()));

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
