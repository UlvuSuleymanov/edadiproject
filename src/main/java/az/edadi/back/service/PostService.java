package az.edadi.back.service;

import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.post.PostVote;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.model.response.SearchResultResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

     Post createPost(PostRequestModel postRequestModel, String username);
     List<PostResponseModel> getPosts(Integer page, Integer size, String sort);


     List<PostResponseModel> getSpecialityyPosts(Long code, Integer page,Integer size, String sort);
     List<PostResponseModel> getUniversityPosts(String uniAbbr, Integer page,Integer size, String sort);
     PostResponseModel toResponse(Post post);
     String savePostPicture(Long id, MultipartFile multipartFile);
     PostVote likePost(long postId, Long userId);
     void  disLikePost(long postId, Long userId);
     boolean checkUserIsLiked(Long userId,Long postId);
     List<PostResponseModel> postsToResponseModels(List<Post> posts);
     List<SearchResultResponseModel> searchPostTitle(String postTitle);
     PostResponseModel getPost(Long postId);

}
