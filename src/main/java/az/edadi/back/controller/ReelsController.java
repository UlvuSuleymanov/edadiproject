package az.edadi.back.controller;

import az.edadi.back.model.response.ReelsRes;
import az.edadi.back.service.ReelsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/reels")
public class ReelsController {

    private final ReelsService reelsService;

    public ReelsController(ReelsService reelsService) {
        this.reelsService = reelsService;
    }


    @GetMapping
    ResponseEntity<List<ReelsRes>> getReels(@RequestParam(defaultValue = "1") int page) {
      return ResponseEntity.ok(reelsService.getReels(page));
    }
}
