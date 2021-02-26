package com.camaat.first.service;

import com.camaat.first.entity.post.Post;
import com.camaat.first.entity.post.PostVote;
import com.camaat.first.model.request.PostRequestModel;
import com.camaat.first.model.response.PostResponseModel;
import com.camaat.first.model.response.SearchResultResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
     Post createPost(PostRequestModel postRequestModel, String username);
     List<PostResponseModel> getPosts(Integer page, Integer size, String sort, Boolean isAuthenticated);
     PostResponseModel getPostResponse(Post post, boolean isAuthenticated);
     String savePostPicture(Long id, MultipartFile multipartFile);
      PostVote likePost(long postId, Long userId);
     void  disLikePost(long postId, Long userId);
     List<SearchResultResponseModel> searchPostTitle(String postTitle);
     PostResponseModel getPost(Long postId,boolean isAuthenticated);

}
