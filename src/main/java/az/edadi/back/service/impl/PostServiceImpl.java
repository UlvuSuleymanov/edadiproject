package az.edadi.back.service.impl;

import az.edadi.back.entity.Topic;
import az.edadi.back.entity.User;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.post.Vote;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.University;
import az.edadi.back.exception.model.BadParamsForPostListException;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.repository.*;
import az.edadi.back.service.PostService;
import az.edadi.back.utility.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UniversityRepository universityRepository;
    private final VoteRepository voteRepository;
    private final SpecialityRepository specialityRepository;
    private final TopicRepository topicRepository;
    private final EntityManager entityManager;

    @Override
    public Post createPost(PostRequestModel postRequestModel) {

        User user = userRepository.findById(AuthUtil.getCurrentUserId()).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id " + AuthUtil.getCurrentUserId().toString())
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
                        new EntityNotFoundException("Topic not found with  id : ")
                );
                post.setTopic(topic);
                break;

        }

        return postRepository.save(post);

    }


    @Override
    public void deletePost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (
                post.isPresent() &&
                        AuthUtil.userIsAuthenticated() &&
                        post.get().getUser().getId().equals(AuthUtil.getCurrentUserId())
        )
            postRepository.delete(post.get());

    }

    @Override
    public List<PostResponseModel> getPostList(String parent, Long id, int page, String sort, boolean asc) {

        //against sql injection
        if (!parent.equals("university") &&
                !parent.equals("speciality") &&
                !parent.equals("topic")) {
            throw new BadParamsForPostListException();

        }

        switch (sort) {
            case "like":
                sort = "SIZE(p.votes)";
                break;
            case "comment":
                sort = "SIZE(p.comments)";
                break;
            case "date":
                sort = "date";
                break;
            default:
                throw new BadParamsForPostListException();
        }

        Query query = createQuery(parent, id, page, sort, asc);
        List<Post> postList = query.getResultList();
        return getPostResponseList(postList);
    }

    List<PostResponseModel> getPostResponseList(List<Post> postList) {

        List<PostResponseModel> postResponseModelList = postList.stream().map(
                post -> new PostResponseModel(post, false)
        ).collect(Collectors.toList());

        if (AuthUtil.userIsAuthenticated())
            return setIsLikes(postResponseModelList);

        return postResponseModelList;
    }

    List<PostResponseModel> setIsLikes(List<PostResponseModel> postResponseModels) {
        List<Long> ids = postResponseModels.stream().map(postResponseModel -> postResponseModel.getId())
                .collect(Collectors.toList()
                );
        List<Vote> voteList = postRepository.getVotes(ids, AuthUtil.getCurrentUserId());
        for (PostResponseModel postResponseModel : postResponseModels)
            for (Vote v : voteList)
                if (v.getPost().getId().equals(postResponseModel.getId()))
                    postResponseModel.setIsLiked(true);

        return postResponseModels;
    }


    Query createQuery(String parent, Long id, int page, String sort, boolean asc) {
        String direction = asc ? " ASC" : " DESC";
        Query query = entityManager.createQuery("SELECT p FROM Post p where p." + parent + ".id=" + id.toString() + " ORDER BY " + sort + direction).
                setFirstResult(calculateOffset(page, 20))
                .setMaxResults(20);
        return query;
    }

    private int calculateOffset(int page, int limit) {
        return ((limit * page) - limit);
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
                .map(post -> new PostResponseModel(post, false))
                .collect(Collectors.toList());

    }

    @Override
    public PostResponseModel getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()) {
            System.out.println("present");

            return new PostResponseModel(post.get(), false);
        }

        return null;
    }


}