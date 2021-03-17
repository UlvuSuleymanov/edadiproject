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
     List<PostResponseModel> getPosts(Integer page, Integer size, String sort);
     List<PostResponseModel> getUniversityPosts(String uniAbbr, Integer page,Integer size, String sort);
     String savePostPicture(Long id, MultipartFile multipartFile);
     PostVote likePost(long postId, Long userId);
     void  disLikePost(long postId, Long userId);

     boolean checkUserIsLiked(Long userId,Long postId);
     List<PostResponseModel> postsToResponseModels(List<Post> posts);
     List<SearchResultResponseModel> searchPostTitle(String postTitle);
     PostResponseModel getPost(Long postId);

}
