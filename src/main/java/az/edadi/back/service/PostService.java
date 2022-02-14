package az.edadi.back.service;

import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.post.Vote;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;

import java.util.List;

public interface PostService {

     List<PostResponseModel> getPostList(String parent, Long id, int page , String sort, boolean asc);

     Post createPost(PostRequestModel postRequestModel);

     void deletePost(Long postId);

     Vote likePost(long postId, Long userId);

     void  disLikePost(long postId, Long userId);

     List<PostResponseModel> searchPost(String text, String type, String id);

     PostResponseModel getPost(Long postId);

}
