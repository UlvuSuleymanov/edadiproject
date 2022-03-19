package az.edadi.back.controller;

import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.service.TopicService;
import az.edadi.back.utility.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/topic")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    ResponseEntity addTopic(@RequestBody TopicRequestModel topicRequestModel) {
        log.info("User {} add topic", AuthUtil.getCurrentUsername());
        return ResponseEntity.ok(topicService.addTopic(topicRequestModel));
    }

    @GetMapping
    ResponseEntity getTopics(@RequestParam(defaultValue = "0") int page) {
        log.info("User {} fetch topics", AuthUtil.getCurrentUsername());
        return ResponseEntity.ok(topicService.getTopicList(page));
    }

    @GetMapping("/{slug}")
    ResponseEntity getTopic(@PathVariable String slug) {
        return ResponseEntity.ok(topicService.getTopic(slug));
    }

    @DeleteMapping("/{id}")
    ResponseEntity getTopic(@PathVariable Long id) {
        log.info("User {} trying to delete topic {}", AuthUtil.getCurrentUsername(), id);
        topicService.deleteTopic(id);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}
