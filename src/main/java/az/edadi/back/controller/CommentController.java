package az.edadi.back.controller;

import az.edadi.back.model.request.CommentRequestModel;
import az.edadi.back.model.request.GetCommentListRequestParamsModel;
import az.edadi.back.model.response.CommentResponseModel;
import az.edadi.back.repository.CommentRepository;
import az.edadi.back.service.CommentService;
import az.edadi.back.utility.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;


    @PostMapping(value = "/api/comment")
    public ResponseEntity addComment(@RequestBody @Valid CommentRequestModel commentRequestModel) {
        log.info("User {} add comment to post with id {}", AuthUtil.getCurrentUsername(), commentRequestModel);
        CommentResponseModel commentResponseModel = commentService.addComment(commentRequestModel);
        return ResponseEntity.ok(commentResponseModel);
    }

    @GetMapping(value = "/api/comment")
    public ResponseEntity getComments(@ModelAttribute @Valid GetCommentListRequestParamsModel getCommentListRequestParamsModel) {
        log.info("User {} fetch comments of post with id {}", AuthUtil.getCurrentUsername(), getCommentListRequestParamsModel.getPostId());
        List<CommentResponseModel> commentResponseModelList = commentService.getComments(getCommentListRequestParamsModel);
        return ResponseEntity.ok(commentResponseModelList);
    }

    @GetMapping(value = "/api/comment/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity getComments(@RequestParam(defaultValue = "0") int page) {
        log.info("User {} fetch all users", AuthUtil.getCurrentUsername());
        return ResponseEntity.ok(
                commentRepository.findAll(PageRequest.of(page,20, Sort.by("date").descending())).toList().stream().map(
                        comment -> new CommentResponseModel(comment,false)
                )
        );
    }

}
