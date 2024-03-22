package az.edadi.back.controller;

import az.edadi.back.entity.post.Post;
import az.edadi.back.model.request.GetPostRequestModel;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/v1/post")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity savePost(@RequestBody @Valid PostRequestModel postRequestModel) {
        Post post = postService.createPost(postRequestModel);
        return ResponseEntity.ok(new PostResponseModel(post, false));
    }

    @GetMapping
    public ResponseEntity getPostsResponse(@ModelAttribute @Valid GetPostRequestModel getPostRequestModel) {
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
    public ResponseEntity searchPost(@ModelAttribute @Valid GetPostRequestModel getPostRequestModel) {
        return ResponseEntity.ok(postService.searchPost(getPostRequestModel));
    }

    @GetMapping(value = "{postId}")
    ResponseEntity<PostResponseModel> getPost(@PathVariable Long postId) {
        PostResponseModel postResponseModel = postService.getPost(postId);
        return ResponseEntity.ok(postResponseModel);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
