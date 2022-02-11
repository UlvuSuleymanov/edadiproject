package az.edadi.back.controller;

import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.post.Vote;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.repository.PostRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.PostService;
import az.edadi.back.service.TagService;
import az.edadi.back.utility.AuthUtil;
import az.edadi.back.utility.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final UserRepository userRepository;
    private final TagService tagService;

    @PostMapping(value = "/post")
//    @PreAuthorize(value = "#username == authentication.name")
    public ResponseEntity addPost(@RequestBody PostRequestModel postRequestModel,
                                  @RequestParam(value = "image", required = false) MultipartFile multipartFile,
                                  @AuthenticationPrincipal String username
    ) {

        Post post = postService.createPost(postRequestModel, username);


        return ResponseEntity.ok(new PostResponseModel(post, false));
    }


    @GetMapping(value = "/university/{abbr}/posts")
    ResponseEntity getUniversityPost(@PathVariable String abbr,
                                     @RequestParam Integer page,
                                     @RequestParam Integer size,
                                     @RequestParam String sort) {


        return ResponseEntity.ok().body(postService.getUniversityPosts(abbr, page, size, sort));
    }

    @GetMapping(value = "/speciality/{code}/posts")
    ResponseEntity getSpecialityPosts(@PathVariable Long code,
                                      @RequestParam Integer page,
                                      @RequestParam Integer size,
                                      @RequestParam String sort) {


        return ResponseEntity.ok().body(postService.getSpecialityPosts(code, page, size, sort));
    }

    @GetMapping(value = "/topic/{slug}/posts")
    ResponseEntity getTopicPosts(@PathVariable String slug,
                                 @RequestParam(required = false) Integer page,
                                 @RequestParam(required = false) Integer size,
                                 @RequestParam(required = false) String sort) {


        return ResponseEntity.ok().body(postService.getTopicPosts(SlugUtil.getId(slug), page, size, sort));
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


    @GetMapping("/posts")
    ResponseEntity getPosts(@RequestParam Integer page,
                            @RequestParam Integer size,
                            @RequestParam String sort
    ) {

        List<PostResponseModel> postResponseModelList = postService.getPosts(page, size, sort);
        return ResponseEntity.ok(postResponseModelList);
    }


    @PostMapping("/post/{postId}/like")
    public ResponseEntity likePost(@PathVariable Long postId) {

        Long userId = AuthUtil.getCurrentUserId();
        Vote postVote = postService.likePost(postId, userId);
        return ResponseEntity.ok(postVote);
    }


    @DeleteMapping("/post/{postId}/like")
    public ResponseEntity disLikePost(@PathVariable Long postId) {

        Long userId = AuthUtil.getCurrentUserId();
        postService.disLikePost(postId, userId);
        return new ResponseEntity(HttpStatus.OK);

    }


}
