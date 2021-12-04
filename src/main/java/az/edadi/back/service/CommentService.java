package az.edadi.back.service;

import az.edadi.back.model.request.CommentRequestModel;
import az.edadi.back.model.response.CommentResponseModel;

import java.util.List;

public interface CommentService {
    CommentResponseModel addComment(CommentRequestModel commentRequestModel, Long postId);

    List<CommentResponseModel> getComments(Long postId, int page, int size, String sort);

    void likeComment(Long commentId, Long userId);

    void disLikeComment(Long commentId, Long userId);
}
