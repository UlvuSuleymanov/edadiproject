package az.edadi.back.controller;

import az.edadi.back.model.request.CommentRequestModel;
import az.edadi.back.model.request.GetCommentListRequestParamsModel;
import az.edadi.back.model.response.CommentResponseModel;
import az.edadi.back.service.CommentService;
import az.edadi.back.utility.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/api/comment")
    public ResponseEntity addComment(@RequestBody @Valid CommentRequestModel commentRequestModel) {
        log.info("User {} add comment to post with id {}", AuthUtil.getCurrentUserId(), commentRequestModel);
        CommentResponseModel commentResponseModel = commentService.addComment(commentRequestModel);
        return ResponseEntity.ok(commentResponseModel);
    }

    @GetMapping(value = "/api/comment")
    public ResponseEntity getComments(@ModelAttribute @Valid GetCommentListRequestParamsModel getCommentListRequestParamsModel) {
        log.info("User {} fetch comments of post with id {}", AuthUtil.getCurrentUserId(), getCommentListRequestParamsModel.getPostId());
        List<CommentResponseModel> commentResponseModelList = commentService.getComments(getCommentListRequestParamsModel);
        return ResponseEntity.ok(commentResponseModelList);
    }

}
