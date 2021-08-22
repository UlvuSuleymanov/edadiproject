package az.edadi.back.controller;

import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final PostService postService;

    public SearchController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    ResponseEntity getUniversityPostsLikeText(@RequestParam String type,
                                              @RequestParam String id,
                                              @RequestParam String text) {
        List<PostResponseModel> postSearchList = postService.searchPost(text, type, id);
        return ResponseEntity.ok(postSearchList);

    }


}
