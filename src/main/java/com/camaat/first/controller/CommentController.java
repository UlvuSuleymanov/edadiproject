package com.camaat.first.controller;

import com.camaat.first.model.request.CommentRequestModel;
import com.camaat.first.model.response.CommentResponseModel;
import com.camaat.first.repository.CommentRepository;
import com.camaat.first.service.CommentService;
import com.camaat.first.entity.Comment;
import com.camaat.first.utility.AuthUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
 public class CommentController {

  private final CommentService commentService;
  private final CommentRepository commentRepository;

  public CommentController(CommentService commentService, CommentRepository commentRepository) {
    this.commentService = commentService;
    this.commentRepository = commentRepository;
  }

//  @PreAuthorize(value = "#username == authentication.name")
  @PostMapping(value = "/api/post/{postId}/comment")
    public ResponseEntity addComment(@PathVariable Long postId,
                                     @RequestBody CommentRequestModel commentRequestModel){

     Comment comment = commentService.commentBuilder(commentRequestModel,postId);
     comment=commentRepository.save(comment);
     CommentResponseModel commentResponseModel=commentService.commentResponseBuilder(comment);
     return ResponseEntity.ok(commentResponseModel);
   }

  @GetMapping(value = "/api/post/{postId}/comments")
   public ResponseEntity getComments(@PathVariable Long postId,
                                     @RequestParam(defaultValue = "5") int page ,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "mostLiked") String sort,
                                     @RequestParam(defaultValue = "false") Boolean authenticated ){
   List<CommentResponseModel> commentResponseModelList =commentService.getComments(postId,page,size,sort);
      System.out.println(commentResponseModelList);
   return ResponseEntity.ok(commentResponseModelList);
  }

    @PostMapping("/api/comments/{commentId}/like")
    public  ResponseEntity likePost(@PathVariable Long commentId){
        Long userId = AuthUtil.getCurrentUserId();
       Long id=commentService.likeComment(commentId,userId);
         return ResponseEntity.ok(id);
    }

    @PostMapping("/api/comments/{commentId}/dislike")
        public  ResponseEntity disLikePost(@PathVariable Long commentId){
        Long userId = AuthUtil.getCurrentUserId();
      commentService.disLikeComment(commentId,userId);
         return new ResponseEntity(HttpStatus.OK);    }




}
