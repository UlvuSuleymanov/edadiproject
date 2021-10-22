package az.edadi.back.controller;

import az.edadi.back.model.request.RoommateRequestModel;
import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.model.response.RoommateResponseModel;
import az.edadi.back.repository.PostRepository;
import az.edadi.back.repository.RoomMateRepository;
import az.edadi.back.service.RoomMateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/postlar")
public class Test {

    @Autowired
    private PostRepository postRepository;


    @GetMapping
    Set<PostResponseModel> getRoommateAds(@RequestParam int page, @RequestParam String sort){
     int size = 10;
     Sort s = Sort.by(sort).descending();
     Pageable pageAble = PageRequest.of(page,size,s);
     return postRepository.findAll(pageAble).stream()
     .map(post -> new PostResponseModel(post,false))
     .collect(Collectors.toSet());
    }

}
