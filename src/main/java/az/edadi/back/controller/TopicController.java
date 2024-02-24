package az.edadi.back.controller;

import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.model.response.TopicResponse;
import az.edadi.back.service.QuestionService;
import az.edadi.back.utility.AuthUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/topic")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TopicController {

    private final QuestionService questionService;
    @PostMapping
    ResponseEntity addQuestion(@RequestBody TopicRequestModel topicRequestModel) {
        log.info("User {} add question", AuthUtil.getCurrentUsername());
        return ResponseEntity.ok(questionService.addQuestion(topicRequestModel));
    }

    @GetMapping
    ResponseEntity getQuestionList(@RequestParam(defaultValue = "0") int page) throws InterruptedException {
        log.info("User {} fetch question", AuthUtil.getCurrentUsername());
        Thread.sleep(3000);
        List<TopicResponse> questionsList = questionService.getQuestionsList(page);
        return ResponseEntity.ok(questionsList);
    }


    @GetMapping("/{slug}")
    ResponseEntity getQuestion(@PathVariable String slug) {
        return ResponseEntity.ok(questionService.getQuestion(slug));
    }

    @GetMapping("/search")
    ResponseEntity searchQuestion(@RequestParam @NotBlank String text, @RequestParam(defaultValue = "0") int page) {
        List<TopicResponse> questionResponseModels = questionService.searchQuestion(text, page);
        return ResponseEntity.ok(questionResponseModels);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteQuestion(@PathVariable Long id) {
        log.info("User {} trying to delete question {}", AuthUtil.getCurrentUsername(), id);
        questionService.deleteQuestion(id);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}
