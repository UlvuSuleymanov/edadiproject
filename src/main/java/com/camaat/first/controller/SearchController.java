package com.camaat.first.controller;

import com.camaat.first.model.response.SearchResultResponseModel;
import com.camaat.first.service.UserService;
import com.camaat.first.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/post/title/{postTitle}")
    ResponseEntity searchPost(@PathVariable String postTitle){
        List<SearchResultResponseModel> postSearchList= postService.searchPostTitle(postTitle);
        return ResponseEntity.ok(postSearchList);


    }
}
