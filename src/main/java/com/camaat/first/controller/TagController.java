package com.camaat.first.controller;

import com.camaat.first.model.response.TagResponseModel;
import com.camaat.first.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {

        this.tagService = tagService;
    }

    @GetMapping
    ResponseEntity getTags() {
     Set<TagResponseModel> tagResponseModelSet = tagService.getTags(40);
     return ResponseEntity.ok(tagResponseModelSet);
    }
}