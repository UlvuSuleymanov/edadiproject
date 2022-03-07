package az.edadi.back.controller;

import az.edadi.back.entity.post.Post;
import az.edadi.back.model.request.GetPostRequestModel;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/post")
    public ResponseEntity addPost(@RequestBody @Valid PostRequestModel postRequestModel) {
        Post post = postService.createPost(postRequestModel);
        return ResponseEntity.ok(new PostResponseModel(post, false));
    }

    @GetMapping(value = "/post")
    public ResponseEntity getPostsResponse(@ModelAttribute @Valid GetPostRequestModel getPostRequestModel) {
        return ResponseEntity.ok(postService.getPostList(getPostRequestModel));
    }

    @GetMapping(value = "/post/search")
    public ResponseEntity searchPost(@ModelAttribute @Valid GetPostRequestModel getPostRequestModel) {
        return ResponseEntity.ok(postService.searchPost(getPostRequestModel));
    }

    @GetMapping(value = "/post/{postId}")
    ResponseEntity<PostResponseModel> getPost(@PathVariable Long postId) {
        PostResponseModel postResponseModel = postService.getPost(postId);
        return ResponseEntity.ok(postResponseModel);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
