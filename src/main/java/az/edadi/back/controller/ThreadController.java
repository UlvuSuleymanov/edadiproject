package az.edadi.back.controller;

import az.edadi.back.model.request.ThreadRequestModel;
import az.edadi.back.model.response.ThreadResponseModel;
import az.edadi.back.service.ThreadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThreadController {
    private final ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @PostMapping("api/thread")
    ResponseEntity createThread(@RequestBody ThreadRequestModel threadRequestModel){

        return ResponseEntity.ok(threadService.createThread(threadRequestModel));
    }
    @GetMapping("api/thread/me")
    ResponseEntity getThreads(@RequestParam(defaultValue = "0") Long page){
      List<ThreadResponseModel> threadResponseModelList= threadService.getThreads(page.intValue());
        return   ResponseEntity.ok(threadResponseModelList);
    }
}
