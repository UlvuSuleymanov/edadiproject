package az.edadi.back.service.impl;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.entity.Topic;
import az.edadi.back.entity.User;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.post.Vote;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.University;
import az.edadi.back.exception.model.BadParamsForPostListException;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.request.GetPostRequestModel;
import az.edadi.back.model.request.PostRequestModel;
import az.edadi.back.model.response.PostResponseModel;
import az.edadi.back.repository.*;
import az.edadi.back.service.PostService;
import az.edadi.back.utility.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UniversityRepository universityRepository;
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
        log.info("User {} trying to delete post {}", AuthUtil.getCurrentUsername(), postId);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException()
        );
        if (!post.getUser().getId().equals(AuthUtil.getCurrentUserId()) &&
                !AuthUtil.hasAuthority(UserAuthority.ADMIN_UPDATE))
            throw new UserAuthorizationException();

        postRepository.delete(post);
    }


    @Override
    public List<PostResponseModel> getPostList(GetPostRequestModel getPostRequestModel) {
        Query query = createGetPostListQuery(getPostRequestModel.getType(),
                getPostRequestModel.getId(),
                getPostRequestModel.getPage(),
                getPostSortQuery(getPostRequestModel.getSort()),
                getPostRequestModel.isAsc()
        );
        List<Post> postList = query.getResultList();

        return getPostResponseList(postList);
    }

    @Override
    public List<PostResponseModel> getPostResponseList(List<Post> postList) {

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


    Query createGetPostListQuery(String type, Long id, int page, String sort, boolean asc) {
        String direction = asc ? " ASC" : " DESC";
        Query query = entityManager.createQuery("SELECT p FROM Post p where p." + type + ".id=" + id.toString() + " ORDER BY " + sort + direction).
                setFirstResult(calculateOffset(page, 10))
                .setMaxResults(10);
        return query;
    }

    Query createSearchQuery(String type, Long id, int page, String sort, boolean asc, String text) {
        String direction = asc ? " ASC" : " DESC";
        Query query = entityManager.createQuery("SELECT p FROM Post p where p.text like '%" + text.trim() + "%' and p." + type + ".id=" + id.toString() + " ORDER BY " + sort + direction).
                setFirstResult(calculateOffset(page, 10))
                .setMaxResults(10);
        return query;
    }


    private int calculateOffset(int page, int limit) {
        return ((limit * page) - limit);
    }


    @Override
    public List<PostResponseModel> searchPost(GetPostRequestModel getPostRequestModel) {

        Query query = createSearchQuery(getPostRequestModel.getType(),
                getPostRequestModel.getId(),
                getPostRequestModel.getPage(),
                getPostSortQuery(getPostRequestModel.getSort()),
                getPostRequestModel.isAsc(), getPostRequestModel.getSearchText()
        );
        List<Post> postList = query.getResultList();

        return getPostResponseList(postList);


    }

    @Override
    public PostResponseModel getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException()
        );

        return setIsLikes(Arrays.asList(new PostResponseModel(post, false))).get(0);
    }

    String getPostSortQuery(String sortField) {
        switch (sortField) {
            case "like":
                return "SIZE(p.votes)";
            case "comment":
                return "SIZE(p.comments)";
            case "date":
                return "date";
            default:
                throw new BadParamsForPostListException();
        }
    }


}