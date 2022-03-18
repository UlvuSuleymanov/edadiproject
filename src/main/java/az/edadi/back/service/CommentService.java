package az.edadi.back.service;

import az.edadi.back.model.request.CommentRequestModel;
import az.edadi.back.model.request.GetCommentListRequestParamsModel;
import az.edadi.back.model.response.CommentResponseModel;

import java.util.List;

public interface CommentService {

    CommentResponseModel addComment(CommentRequestModel commentRequestModel);

    List<CommentResponseModel> getComments(GetCommentListRequestParamsModel getCommentListRequestParamsModel);

    void deleteComment(Long id);

}
