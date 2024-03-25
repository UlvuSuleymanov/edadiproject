package az.edadi.back.controller;

import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.model.response.TopicResponse;
import az.edadi.back.service.QuestionService;
import az.edadi.back.utility.AuthUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/topic")
public class TopicController {
    private final QuestionService questionService;
    public TopicController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @PostMapping
    ResponseEntity<TopicResponse> addQuestion(@RequestBody TopicRequestModel topicRequestModel) {
        log.info("User {} add topic", AuthUtil.getCurrentUsername());
        return ResponseEntity.ok(questionService.addQuestion(topicRequestModel));
    }
    @GetMapping
    ResponseEntity<List<TopicResponse>> getQuestionList(@RequestParam(defaultValue = "0") int page) throws InterruptedException {
        log.info("User {} fetch topics", AuthUtil.getCurrentUsername());
        return ResponseEntity.ok(questionService.getQuestionsList(page));
    }

    @GetMapping("{slug}")
    ResponseEntity<TopicResponse> getQuestion(@PathVariable String slug) {
        return ResponseEntity.ok(questionService.getQuestion(slug));
    }

    @GetMapping("search")
    ResponseEntity<List<TopicResponse>> searchQuestion(@RequestParam @NotBlank String text, @RequestParam(defaultValue = "0") int page) {
         return ResponseEntity.ok(questionService.searchQuestion(text, page));
    }

    @DeleteMapping("{id}")
    ResponseEntity<Object> deleteQuestion(@PathVariable Long id) {
        log.info("User {} trying to delete topic with id {}", AuthUtil.getCurrentUsername(), id);
        questionService.deleteQuestion(id);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}
