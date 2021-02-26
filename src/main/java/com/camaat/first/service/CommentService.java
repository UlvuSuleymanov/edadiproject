package com.camaat.first.service;

import com.camaat.first.entity.post.Comment;
import com.camaat.first.model.request.CommentRequestModel;
import com.camaat.first.model.response.CommentResponseModel;

import java.util.List;

public interface CommentService {
    Comment commentBuilder(CommentRequestModel commentRequestModel, Long postId);
    CommentResponseModel commentResponseBuilder(Comment comment);
    List<CommentResponseModel> getComments(Long postId, int page, int size, String sort);
    Long likeComment(Long commentId,Long userId);
    void disLikeComment(Long commentId,Long userId);
}
