package az.edadi.back.controller;

import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.model.response.SearchResultResponseModel;
import az.edadi.back.service.PostService;
import az.edadi.back.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private  final UserService userService;
    private  final PostService postService;

    public SearchController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }


//    @GetMapping("/post/title/{postTitle}")
//    ResponseEntity searchPost(@PathVariable String postTitle){
//        List<SearchResultResponseModel> postSearchList= postService.searchPost(postTitle);
//        return ResponseEntity.ok(postSearchList);
//
//    }

    @GetMapping("/post")
    ResponseEntity getUniversityPostsLikeText(@RequestParam String type,
                                              @RequestParam String id,
                                              @RequestParam String text ){
        List<PostResponseModel> postSearchList= postService.searchPost(text,type,id);
        return ResponseEntity.ok(postSearchList);

    }


}
