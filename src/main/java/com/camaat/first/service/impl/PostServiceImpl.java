package com.camaat.first.service.impl;

import com.camaat.first.entity.post.Post;
import com.camaat.first.entity.post.PostVote;
import com.camaat.first.entity.post.Tag;
import com.camaat.first.entity.User;
import com.camaat.first.entity.university.University;
import com.camaat.first.model.response.PostResponseModel;
import com.camaat.first.repository.PostRepository;
import com.camaat.first.repository.PostVoteRepository;
import com.camaat.first.repository.UniversityRepository;
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
 import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Service
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TagServiceImpl tagService;
    private final UniversityRepository universityRepository;
    private final S3Service s3Service;
    private final ImageService imageService;
    private final PostVoteRepository postVoteRepository;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository, TagServiceImpl tagService, UniversityRepository universityRepository, S3Service s3Service, ImageService imageService, PostVoteRepository postVoteRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.tagService = tagService;
        this.universityRepository = universityRepository;
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
        post.setUniversity(universityRepository.getOne(1L));
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
    public List<PostResponseModel> getPosts(Integer page, Integer size, String sort) {

        Pageable pageable = PageRequest.of(page, size);
        Optional<List<Post>> postList = Optional.empty();


        switch (sort) {
            case "mostCommented":
                postList = Optional.ofNullable(postRepository.getTopCommentPost(pageable));
                break;
            case "mostLiked":
                postList = Optional.ofNullable(postRepository.getTopLikedPost(pageable));
                break;
            case "three":
                System.out.println("mostLiked");
                break;
            default:
                postList = Optional.of(postRepository.findAll(pageable).toList());
        }
        if (postList.isPresent()) {
            return postsToResponseModels(postList.get());
        }

        return null;
    }

    @Override
    public List<PostResponseModel> getUniversityPosts(String uniAbbr,
                                                      Integer page,
                                                      Integer size,
                                                      String sort) {


        Optional<University> universityOptional = universityRepository.findByAbbr(uniAbbr);
        List<Post> postList = null;
        Pageable pageable = PageRequest.of(page, size);

        if (universityOptional.isPresent()) {

            switch (sort) {

                case "mostCommented":
                    postList = postRepository.getTopCommentUniversityPost(universityOptional.get().getId(), pageable);
                    break;
                case "mostLiked":
                    postList = postRepository.getTopLikedUniversityPost(universityOptional.get().getId(), pageable);
                    break;

                default:
                    postList = postRepository.findAll(pageable).toList();

            }

            Optional<List<Post>> postsOptional = Optional.ofNullable(postList);
            if (postsOptional.isPresent())
                return postsToResponseModels(postList);
        }
        return null;


    }

    @Override
    public PostResponseModel toResponse(Post post) {

        boolean authenticated = AuthUtil.userIsAuthenticated();
        Long userId = null;


        if (authenticated) {
            userId = AuthUtil.getCurrentUserId();
        }
        boolean isLiked = false;
        if (authenticated) {
            isLiked = checkUserIsLiked(userId, post.getId());

        }

        return new PostResponseModel(post, isLiked);


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
    public boolean checkUserIsLiked(Long userId, Long postId) {
        return postVoteRepository.getPostVoteByIds(userId, postId) != null;

    }

    @Override
    public List<PostResponseModel> postsToResponseModels(List<Post> posts) {

        List<PostResponseModel> postResponseModelList = new ArrayList<>();

        for (Post post : posts) {

            postResponseModelList.add(toResponse(post));
        }

        return postResponseModelList;


    }

    @Override
    public List<SearchResultResponseModel> searchPostTitle(String postTitle) {
        Pageable pageable = PageRequest.of(0, 10);

        List<SearchResultResponseModel> searchResult = postRepository.getPostLikeTitle(postTitle, pageable);

        return searchResult;
    }

    @Override
    public PostResponseModel getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);


        if (post.isPresent()) {
            return toResponse(post.get());
        }

        return null;
    }
}