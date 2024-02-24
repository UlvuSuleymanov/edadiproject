package az.edadi.back.controller;

import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.entity.post.Post;
import az.edadi.back.model.request.GetPostRequestModel;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.repository.PostRepository;
import az.edadi.back.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping(value = "/post")
    public ResponseEntity addPost(@RequestBody @Valid PostRequestModel postRequestModel) {
        Post post = postService.createPost(postRequestModel);
        return ResponseEntity.ok(new PostResponseModel(post, false));
    }

    @GetMapping(value = "/post")
    public ResponseEntity getPostsResponse(@ModelAttribute @Valid GetPostRequestModel getPostRequestModel) {
        return ResponseEntity.ok(postService.getPostList(getPostRequestModel));
    }

    @GetMapping(value = "/post/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity getAllPosts(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(
                postService.getPostResponseList(postRepository.findAll(PageRequest.of(page,20, Sort.by("date").descending())).toList())
        );
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
