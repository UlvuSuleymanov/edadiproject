package az.edadi.back.service;

import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.post.Vote;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

     Post createPost(PostRequestModel postRequestModel, String username);

     List<PostResponseModel> getPosts(Integer page, Integer size, String sort);
     List<PostResponseModel> getTopicPosts(Long id, Integer page,Integer size, String sort);
     List<PostResponseModel> getSpecialityPosts(Long code, Integer page,Integer size, String sort);
     List<PostResponseModel> getUniversityPosts(String uniAbbr, Integer page,Integer size, String sort);

     PostResponseModel toResponse(Post post);
     String savePostPicture(Long id, MultipartFile multipartFile);
     Vote likePost(long postId, Long userId);
     void  disLikePost(long postId, Long userId);
     boolean checkUserIsLiked(Long userId,Long postId);
     List<PostResponseModel> postsToResponseModels(List<Post> posts);

     List<PostResponseModel> searchPost(String text, String type, String id);

     PostResponseModel getPost(Long postId);

}
