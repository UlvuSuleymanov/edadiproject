package com.camaat.first.controller;

import com.camaat.first.entity.post.PostVote;
import com.camaat.first.repository.UserRepository;
import com.camaat.first.service.AuthenticationService;
import com.camaat.first.service.TagService;
import com.camaat.first.utility.AuthUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.camaat.first.entity.post.Post;
import com.camaat.first.model.request.PostRequestModel;
import com.camaat.first.model.response.PostResponseModel;
import com.camaat.first.repository.PostRepository;
import com.camaat.first.service.PostService;
import com.camaat.first.utility.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api")
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final UserRepository userRepository;
    private final TagService tagService;

    @Autowired
    public PostController(PostRepository questionRepository, PostService postService, UserRepository userRepository, TagService tagService) {
        this.postRepository = questionRepository;
        this.postService = postService;
        this.userRepository = userRepository;
        this.tagService = tagService;
    }


    @PostMapping(value = "/{username}/post")
    @PreAuthorize(value = "#username == authentication.name")
    public ResponseEntity addPost(@RequestParam(value = "post") String newPostJson,
                                  @RequestParam(value = "image", required = false) MultipartFile multipartFile,
                                  @PathVariable String username) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            PostRequestModel postRequestModel = objectMapper.readValue(newPostJson, PostRequestModel.class);
            postRequestModel.setMultipartFile(multipartFile);
            Post post = postService.createPost(postRequestModel, username);


        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/university/{abbr}/posts")
    ResponseEntity getUniversityPost(@PathVariable String abbr,
                                     @RequestParam Integer page,
                                     @RequestParam Integer size,
                                     @RequestParam String sort){


    return ResponseEntity.ok().body(postService.getUniversityPosts(abbr,page,size,sort));
    }



    @GetMapping(value = "/post/{postId}")
    ResponseEntity<PostResponseModel> getPost(@PathVariable Long postId) {
        PostResponseModel postResponseModel = postService.getPost(postId);


         return ResponseEntity.ok(postResponseModel);
    }

//    @GetMapping(value = "/post/{postId}/{username}")
//    ResponseEntity<PostResponseModel> getPostForCurrentUser(@PathVariable Long postId) {
//        PostResponseModel postResponseModel = postService.getPost(postId, true);
//
//        Optional<Long> id = Optional.ofNullable(postId);
//
//
//        return ResponseEntity.ok(postResponseModel);
//    }


    @GetMapping("/posts")
    ResponseEntity getPosts(@RequestParam Integer page,
                            @RequestParam Integer size,
                            @RequestParam String sort
    ) {


        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(SecurityContextHolder.getContext());


        List<PostResponseModel> postResponseModelList = postService.getPosts(page, size, sort);

        return ResponseEntity.ok(postResponseModelList);

    }


    @PostMapping("/post/{postId}/like")
    public ResponseEntity likePost(@PathVariable Long postId) {

        Long userId =  AuthUtil.getCurrentUserId();
         PostVote postVote = postService.likePost(postId, userId);
        return ResponseEntity.ok(postVote);
    }


    @DeleteMapping("/post/{postId}/like")
    public ResponseEntity disLikePost(@PathVariable Long postId) {

        Long userId = AuthUtil.getCurrentUserId();


        postService.disLikePost(postId, userId);
        return new ResponseEntity(HttpStatus.OK);

    }





}
