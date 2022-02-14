package az.edadi.back.service;

import az.edadi.back.entity.post.Post;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;

import java.util.List;

public interface PostService {

    List<PostResponseModel> getPostList(String type, Long id, int page, String sort, boolean asc);

    Post createPost(PostRequestModel postRequestModel);

    void deletePost(Long postId);

    List<PostResponseModel> searchPost(String text, String type, String id);

    PostResponseModel getPost(Long postId);

}
