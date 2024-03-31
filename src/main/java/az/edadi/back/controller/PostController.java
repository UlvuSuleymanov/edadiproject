package az.edadi.back.controller;

import az.edadi.back.model.request.GetPostRequestModel;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/v1/post")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponseModel> savePost(@RequestBody @Valid PostRequestModel postRequestModel) {
        return ResponseEntity.ok(new PostResponseModel(postService.createPost(postRequestModel), false));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseModel>> getPostList(@ModelAttribute @Valid GetPostRequestModel getPostRequestModel) {
         return ResponseEntity.ok(postService.getPostList(getPostRequestModel));
    }

//    @GetMapping(value = "/post/all")
//    @PreAuthorize("hasAuthority('admin:read')")
//    public ResponseEntity getAllPosts(@RequestParam(defaultValue = "0") int page) {
//        return ResponseEntity.ok(
//                postService.getPostResponseList(postRepository.findAll(PageRequest.of(page,20, Sort.by("date").descending())).toList())
//        );
//    }

    @GetMapping(value = "search")
    public ResponseEntity<List<PostResponseModel>> searchPost(@ModelAttribute @Valid GetPostRequestModel getPostRequestModel) {
        return ResponseEntity.ok(postService.searchPost(getPostRequestModel));
    }

    @GetMapping(value = "{postId}")
    ResponseEntity<PostResponseModel> getPost(@PathVariable Long postId) {
        PostResponseModel postResponseModel = postService.getPost(postId);
        return ResponseEntity.ok(postResponseModel);
    }

    @DeleteMapping("{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }


}
