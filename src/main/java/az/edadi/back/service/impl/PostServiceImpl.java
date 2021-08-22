package az.edadi.back.service.impl;

import az.edadi.back.entity.Topic;
import az.edadi.back.entity.User;
import az.edadi.back.entity.post.Post;
 import az.edadi.back.entity.post.Vote;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.University;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.repository.*;
import az.edadi.back.service.FileService;
import az.edadi.back.service.ImageService;
import az.edadi.back.service.PostService;
import az.edadi.back.utility.AuthUtil;
import az.edadi.back.utility.PhotoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TagServiceImpl tagService;
    private final UniversityRepository universityRepository;
    private final FileService s3Service;
    private final ImageService imageService;
    private final VoteRepository voteRepository;
    private final SpecialityRepository specialityRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public PostServiceImpl(UserRepository userRepository,
                           PostRepository postRepository,
                           TagServiceImpl tagService,
                           UniversityRepository universityRepository,
                           FileService s3Service,
                           ImageService imageService,
                           VoteRepository voteRepository,
                           SpecialityRepository specialityRepository, TopicRepository topicRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.tagService = tagService;
        this.universityRepository = universityRepository;
        this.s3Service = s3Service;
        this.imageService = imageService;
        this.voteRepository = voteRepository;
        this.specialityRepository = specialityRepository;
        this.topicRepository = topicRepository;
    }


    @Override
    public Post createPost(PostRequestModel postRequestModel, String username) {


        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : ")
        );

        Post post = new Post(postRequestModel, user);

        switch (postRequestModel.getType()) {
            case "university":
                University university = universityRepository.findById(postRequestModel.getId()).orElseThrow(() ->
                        new UsernameNotFoundException("University not found with  id : ")
                );
                post.setUniversity(university);
                break;

            case "speciality":
                Speciality speciality = specialityRepository.findById(postRequestModel.getId()).orElseThrow(() ->
                        new EntityNotFoundException("Speciality not found with  id : ")
                );
                post.setSpeciality(speciality);
                break;
            case "topic":
                Topic topic = topicRepository.findById(postRequestModel.getId()).orElseThrow(() ->
                        new UsernameNotFoundException("Topic not found with  id : ")
                );
                post.setTopic(topic);
                break;

        }


        return postRepository.save(post);


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
    public List<PostResponseModel> getTopicPosts(Long id, Integer page, Integer size, String sort) {
        Topic topic = topicRepository.getById(id);
        return postsToResponseModels(topic.getPosts());
    }


    @Override
    public List<PostResponseModel> getSpecialityPosts(Long code, Integer page, Integer size, String sort) {
        Pageable pageable = PageRequest.of(page, size);
        List<Post> postList = postRepository.getSpecialityPosts(code, pageable);

        return postList.stream()
                .map(post -> toResponse(post))
                .collect(Collectors.toList());

//        switch (sort) {
//            case "mostCommented":
//                postList = Optional.ofNullable(postRepository.getTopCommentPost(pageable));
//                break;
//            case "mostLiked":
//                postList = Optional.ofNullable(postRepository.getTopLikedPost(pageable));
//                break;
//            case "three":
//                System.out.println("mostLiked");
//                break;
//            default:
////                postList = Optional.of(postRepository.findAll(pageable).toList());
//        }
//        if (postList.isPresent()) {
//            return postsToResponseModels(postList.get());
//        }

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

        if (post != null) {

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
        return null;
    }


    @Override
    public String savePostPicture(Long id, MultipartFile multipartFile) {
        try {
            File file = s3Service.convertMultiPartToFile(multipartFile);
            String name = "postImage" + id;
            s3Service.save(name, file);
            return PhotoUtil.getFullPhotoUrl(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "default";
    }


    @Override
    public Vote likePost(long postId, Long userId) {
        Post post = postRepository.getOne(postId);
        User user = userRepository.getOne(userId);

        Vote postVote = voteRepository.getPostVoteByIds(userId, postId);
        if (postVote == null) {
            postVote = new Vote();
            postVote.setUser(user);
            postVote.setPost(post);
            postVote.setDate(new Date());
        }
        return voteRepository.save(postVote);


    }

    @Override
    public void disLikePost(long postId, Long userId) {
        Vote postVote = voteRepository.getPostVoteByIds(userId, postId);
        voteRepository.delete(postVote);
    }


    @Override
    public boolean checkUserIsLiked(Long userId, Long postId) {
        return voteRepository.getPostVoteByIds(userId, postId) != null;

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
    public List<PostResponseModel> searchPost(String text, String type, String id) {
        Pageable pageable = PageRequest.of(0, 10);


        List<Post> postList = new ArrayList<>();


        switch (type) {
            case "university":
                postList = postRepository.searchUniversityPostsLikeText(Long.valueOf(id), text, pageable);
                break;
            case "speciality":
                postList = postRepository.searchSpecialityPostsLikeText(Long.valueOf(id), text, pageable);
            case "topic":
                postList = postRepository.searchTopicPostsLikeText(Long.valueOf(id), text, pageable);

        }

        return postList.stream()
                .map(post -> toResponse(post))
                .collect(Collectors.toList());


    }

    @Override
    public PostResponseModel getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);


        if (post.isPresent()) {
            System.out.println("present");

            return toResponse(post.get());
        }

        return null;
    }
}