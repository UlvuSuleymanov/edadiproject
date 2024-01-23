package az.edadi.back.controller;

import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.model.request.ThreadRequestModel;
import az.edadi.back.model.response.ThreadResponseModel;
import az.edadi.back.service.ThreadService;
import az.edadi.back.utility.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/thread")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThreadController {

//    private final ThreadService threadService;
//    private final ApplicationEventPublisher applicationEventPublisher;

//    @PostMapping
//    ResponseEntity createThread(@RequestBody ThreadRequestModel threadRequestModel) {
//        applicationEventPublisher.publishEvent(UserEvent.ADD_THREAD);
//        log.info("User {} send request to a create a thread", AuthUtil.getCurrentUserId());
//        return ResponseEntity.ok(threadService.createThread(threadRequestModel));
//    }
//
//    @GetMapping
//    ResponseEntity getThreads(@RequestParam(defaultValue = "0") Long page) {
//        List<ThreadResponseModel> threadResponseModelList = threadService.getThreads(page.intValue());
//        return ResponseEntity.ok(threadResponseModelList);
//    }
//
//    @GetMapping("/{id}")
//    ResponseEntity getThread(@PathVariable Long id) {
//        return ResponseEntity.ok(threadService.getThread(id));
//    }

}
