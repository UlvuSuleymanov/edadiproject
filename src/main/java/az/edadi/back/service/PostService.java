package az.edadi.back.service;

import az.edadi.back.entity.post.Post;
import az.edadi.back.model.request.GetPostRequestModel;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;

import java.util.List;

public interface PostService {

    PostResponseModel getPost(Long postId);

    List<PostResponseModel> getPostList(GetPostRequestModel getPostRequestModel);

    Post createPost(PostRequestModel postRequestModel);

    void deletePost(Long postId);

    List<PostResponseModel> searchPost(GetPostRequestModel getPostRequestModel);


}
