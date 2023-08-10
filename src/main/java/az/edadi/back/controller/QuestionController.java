package az.edadi.back.controller;

import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.service.QuestionService;
import az.edadi.back.utility.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    ResponseEntity addQuestion(@RequestBody TopicRequestModel topicRequestModel) {
        log.info("User {} add question", AuthUtil.getCurrentUsername());
        return ResponseEntity.ok(questionService.addQuestion(topicRequestModel));
    }

    @GetMapping
    ResponseEntity getQuestions(@RequestParam(defaultValue = "0") int page) {
        log.info("User {} fetch question", AuthUtil.getCurrentUsername());
        return ResponseEntity.ok(questionService.getQuestionsList(page));
    }

    @GetMapping("/{slug}")
    ResponseEntity getQuestion(@PathVariable String slug) {
        return ResponseEntity.ok(questionService.getQuestion(slug));
    }

    @DeleteMapping("/{id}")
    ResponseEntity getTopic(@PathVariable Long id) {
        log.info("User {} trying to delete question {}", AuthUtil.getCurrentUsername(), id);
         questionService.deleteQuestion(id);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}
