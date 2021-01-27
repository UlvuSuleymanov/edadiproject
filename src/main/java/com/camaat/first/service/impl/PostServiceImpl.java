package com.camaat.first.service.impl;

import com.camaat.first.entity.Post;
import com.camaat.first.entity.PostVote;
import com.camaat.first.entity.Tag;
import com.camaat.first.entity.User;
import com.camaat.first.model.response.PostResponseModel;
import com.camaat.first.repository.PostRepository;
import com.camaat.first.repository.PostVoteRepository;
import com.camaat.first.repository.UserRepository;
import com.camaat.first.service.ImageService;
import com.camaat.first.utility.AuthUtil;
 import com.camaat.first.utility.ImageEnum;
import com.camaat.first.utility.ImageUtil;
 import com.camaat.first.model.request.PostRequestModel;
import com.camaat.first.model.response.SearchResultResponseModel;
import com.camaat.first.service.PostService;

import com.camaat.first.service.S3Service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TagServiceImpl tagService;
    private final S3Service s3Service;
    private final ImageService imageService;
    private final PostVoteRepository postVoteRepository;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository, TagServiceImpl tagService, S3Service s3Service, ImageService imageService, PostVoteRepository postVoteRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.tagService = tagService;
        this.s3Service = s3Service;
        this.imageService = imageService;
        this.postVoteRepository = postVoteRepository;
    }


    @Override
    public Post createPost(PostRequestModel postRequestModel, String username) {


        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : ")
        );
        Date date = new Date();
        Post post = new Post();


        post.setDate(date);
        post.setUser(user);
        post.setPostText(postRequestModel.getPostText());
        post.setPostTitle(postRequestModel.getPostTitle());
        user.getPosts().add(post);
        Post savedPost = postRepository.save(post);

        Set<Tag> tagSet = tagService.addTags(postRequestModel.getTags(), savedPost);
        post.setTags(tagSet);
        String photoUrl = ImageEnum.DEFAULT_IMAGE_NAME.getName();
        if (postRequestModel.getMultipartFile() != null) {
            photoUrl = savePostPicture(savedPost.getId(), postRequestModel.getMultipartFile());
        }
        post.setPhotoUrl(photoUrl);
        userRepository.save(user);
        return post;


    }

    @Override
    public List<PostResponseModel> getPosts(Integer page, Integer size, String sort, Boolean isAuthenticated) {
        List<PostResponseModel> postResponseModelList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        switch (sort) {
            case "mostCommented":

                postResponseModelList = postRepository.getTopCommentPost(pageable)
                        .stream()
                        .map(post -> getPostResponse(post, isAuthenticated))
                        .collect(Collectors.toList());
                break;

            case "mostLiked":
                postResponseModelList = postRepository.getTopLikedPost(pageable)
                        .stream()
                        .map(post -> getPostResponse(post, isAuthenticated))
                        .collect(Collectors.toList());
                break;
            case "three":
                System.out.println("mostLiked");
                break;
            default:
                System.out.println("");
        }

        return postResponseModelList;
    }


    @Override
    public PostResponseModel getPostResponse(Post post, boolean isAuthenticated) {
        PostResponseModel postResponseModel = new PostResponseModel();
        if (isAuthenticated) {
            Boolean isLiked = false;
            Long userId =AuthUtil.getCurrentUserId();
            isLiked = postVoteRepository.getPostVoteByIds(userId, post.getId()) != null;
            postResponseModel.setIsLiked(isLiked);
        }
        postResponseModel.setId(post.getId())
                .setAuthorUsername(post.getUser().getUsername())
                .setAuthorName(post.getUser().getName())
                .setDate(post.getDate())
                .setPostCommentCount((long) post.getComments().size())
                .setPostLikeCount((long) post.getPostVote().size())
                .setPostText(post.getPostText())
                .setPhotoUrl(post.getPhotoUrl())
                .setPostTitle(post.getPostTitle())
                .setAuthorPhotoUrl(ImageUtil.generatePhotoUrl(post.getUser().getPhotoUrl()));

        return postResponseModel;
    }

    @Override
    public String savePostPicture(Long id, MultipartFile multipartFile) {
        try {
            File file = imageService.convertMultiPartToFile(multipartFile);
            String name = "postImage" + id;
            s3Service.setPhoto(name, file);
            file.delete();
            return ImageUtil.generatePhotoUrl(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "default";
    }


    @Override
    public PostVote likePost(long postId, Long userId) {
        Post post = postRepository.getOne(postId);
        User user = userRepository.getOne(userId);

        PostVote postVote = postVoteRepository.getPostVoteByIds(userId, postId);
        if (postVote == null) {
            postVote = new PostVote();
            postVote.setUser(user);
            postVote.setPost(post);
            postVote.setDate(new Date());
        }
        return postVoteRepository.save(postVote);


    }

    @Override
    public void disLikePost(long postId, Long userId) {
        PostVote postVote = postVoteRepository.getPostVoteByIds(userId, postId);
        postVoteRepository.delete(postVote);
    }

    @Override
    public List<SearchResultResponseModel> searchPostTitle(String postTitle) {
        Pageable pageable = PageRequest.of(0, 10);

      List<SearchResultResponseModel>searchResult= postRepository.getPostLikeTitle(postTitle,pageable);

      return searchResult;
    }

    @Override
    public PostResponseModel getPost(Long postId,boolean isAuthenticated) {
         Optional<Post> post = postRepository.findById(postId);


     if(post.isPresent())
     {
         return getPostResponse(post.get(),isAuthenticated);
     }
        return null;

    }


}
