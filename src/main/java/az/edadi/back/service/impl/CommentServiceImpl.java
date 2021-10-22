package az.edadi.back.service.impl;

import az.edadi.back.entity.User;
import az.edadi.back.entity.post.Comment;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.post.Vote;
import az.edadi.back.model.request.CommentRequestModel;
import az.edadi.back.model.response.CommentResponseModel;
import az.edadi.back.repository.CommentRepository;
import az.edadi.back.repository.PostRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.repository.VoteRepository;
import az.edadi.back.service.CommentService;
import az.edadi.back.service.ImageService;
import az.edadi.back.utility.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final VoteRepository voteRepository;


    @Autowired
    public CommentServiceImpl(UserRepository userRepository,
                              PostRepository postRepository,
                              CommentRepository commentRepository,
                              ImageService imageService,
                              VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public Comment commentBuilder(CommentRequestModel commentRequestModel, Long postId) {

        Long id = AuthUtil.getCurrentUserId();

        Post post = postRepository.getOne(postId);
        User user = userRepository.getOne(id);


        Comment comment = new Comment();
        comment.setDate(new Date());
        comment.setUser(user);
        comment.setPost(post);
        comment.setCommentText(commentRequestModel.getCommentText());
        return comment;

    }


    @Override
    public List<CommentResponseModel>   getComments(Long postId, int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size);
        List<Comment> commentList = new ArrayList<>();

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            switch (sort) {
                case "mostLiked":
                    commentList = commentRepository.getTopLikedComment(postId, pageable);

                default:
                    commentList = commentRepository.findByPost_id(postId, pageable);

            }

            return commentList.stream()
                    .map(comment -> new CommentResponseModel(comment, false))
                    .collect(Collectors.toList());

        }
        return null;

    }

    @Override
    public void likeComment(Long commentId, Long userId) {
        Optional<Vote> vote = voteRepository.getCommentVoteByIds(userId, commentId);
        if (!vote.isPresent()) {
            Optional<Comment> comment = commentRepository.findById(commentId);

            if (comment.isPresent()) {
                Vote v = new Vote(User.builder().id(userId).build(), comment.get());
                voteRepository.save(v);
            }
        }

    }


    @Override
    public void disLikeComment(Long commentId, Long userId) {
        Optional<Vote> vote = voteRepository.getCommentVoteByIds(userId, commentId);
        if (vote.isPresent())
            voteRepository.delete(vote.get());
    }


}
