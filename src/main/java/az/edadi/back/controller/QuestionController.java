package az.edadi.back.controller;

import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.model.response.QuestionResponseModel;
import az.edadi.back.service.QuestionService;
import az.edadi.back.utility.AuthUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionController {

    private final QuestionService questionService;
    private final ApplicationEventPublisher applicationEventPublisher;
    @PostMapping
    ResponseEntity addQuestion(@RequestBody TopicRequestModel topicRequestModel) {
        log.info("User {} add question", AuthUtil.getCurrentUsername());
        applicationEventPublisher.publishEvent(UserEvent.ADD_QUESTION);
        return ResponseEntity.ok(questionService.addQuestion(topicRequestModel));
    }

    @GetMapping
    ResponseEntity getQuestionList(@RequestParam(defaultValue = "0") int page) {
        log.info("User {} fetch question", AuthUtil.getCurrentUsername());
        List<QuestionResponseModel> questionsList = questionService.getQuestionsList(page);
        return ResponseEntity.ok(questionsList);
    }


    @GetMapping("/{slug}")
    ResponseEntity getQuestion(@PathVariable String slug) {
        return ResponseEntity.ok(questionService.getQuestion(slug));
    }

    @GetMapping("/search")
    ResponseEntity searchQuestion(@RequestParam @NotBlank String text, @RequestParam(defaultValue = "0") int page) {
        List<QuestionResponseModel> questionResponseModels = questionService.searchQuestion(text, page);
        return ResponseEntity.ok(questionResponseModels);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteQuestion(@PathVariable Long id) {
        log.info("User {} trying to delete question {}", AuthUtil.getCurrentUsername(), id);
        questionService.deleteQuestion(id);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}
