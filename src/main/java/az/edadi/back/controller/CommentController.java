package az.edadi.back.controller;

import az.edadi.back.model.request.CommentRequestModel;
import az.edadi.back.model.response.CommentResponseModel;
import az.edadi.back.repository.CommentRepository;
import az.edadi.back.service.CommentService;
import az.edadi.back.utility.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentService commentService, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }

    @PostMapping(value = "/api/post/{postId}/comment")
    public ResponseEntity addComment(@PathVariable Long postId,
                                     @RequestBody CommentRequestModel commentRequestModel) {
        CommentResponseModel commentResponseModel = commentService.addComment(commentRequestModel, postId);
        return ResponseEntity.ok(commentResponseModel);

    }

    @GetMapping(value = "/api/post/{postId}/comments")
    public ResponseEntity getComments(@PathVariable Long postId,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "mostLiked") String sort
    ) {

        List<CommentResponseModel> commentResponseModelList = commentService.getComments(postId, page, size, sort);
        return ResponseEntity.ok(commentResponseModelList);
    }


    @PostMapping("/api/comment/{commentId}/like")
    public ResponseEntity likePost(@PathVariable Long commentId) {
        Long userId = AuthUtil.getCurrentUserId();
        commentService.likeComment(commentId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/api/comment    /{commentId}/like")
    public ResponseEntity disLikePost(@PathVariable Long commentId) {

        Long userId = AuthUtil.getCurrentUserId();

        commentService.disLikeComment(commentId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
