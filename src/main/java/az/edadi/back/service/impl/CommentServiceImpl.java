package az.edadi.back.service.impl;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.entity.User;
import az.edadi.back.entity.post.Comment;
import az.edadi.back.entity.post.Post;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.UserEventModel;
import az.edadi.back.model.request.CommentRequestModel;
import az.edadi.back.model.request.GetCommentListRequestParamsModel;
import az.edadi.back.model.response.CommentResponseModel;
import az.edadi.back.repository.CommentRepository;
import az.edadi.back.repository.PostRepository;
import az.edadi.back.repository.UserEventsRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.CommentService;
import az.edadi.back.utility.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserEventsRepository userEventsRepository;
    @Override
    public CommentResponseModel addComment(CommentRequestModel commentRequestModel) {
        Long id = AuthUtil.getCurrentUserId();
        userEventsRepository.check(new UserEventModel(id, UserEvent.ADD_COMMENT));
        Post post = postRepository.findById(commentRequestModel.getPostId()).orElseThrow(() -> new EntityNotFoundException(
                "No post found with this id"
        ));
        User user = userRepository.getById(id);
        Comment comment = new Comment();
        comment.setDate(new Date());
        comment.setUser(user);
        comment.setPost(post);
        comment.setCommentText(commentRequestModel.getText());
        comment = commentRepository.save(comment);
        return new CommentResponseModel(comment, false);

    }


    @Override
    public List<CommentResponseModel> getComments(GetCommentListRequestParamsModel getCommentListRequestParamsModel) {

        Pageable pageable = PageRequest.of(getCommentListRequestParamsModel.getPage(), 15);
        List<Comment> commentList = commentRepository.findByPost_id(getCommentListRequestParamsModel.getPostId(), pageable);
        return commentList.stream()
                .map(comment -> new CommentResponseModel(comment, false))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        if (!comment.getUser().getId().equals(AuthUtil.getCurrentUserId()) &&
                !AuthUtil.hasAuthority(UserAuthority.ADMIN_UPDATE))
            throw new UserAuthorizationException();
        commentRepository.delete(comment);
    }

}
