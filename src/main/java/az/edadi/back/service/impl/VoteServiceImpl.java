package az.edadi.back.service.impl;

import az.edadi.back.entity.User;
import az.edadi.back.entity.post.Comment;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.post.Vote;
import az.edadi.back.exception.model.PostAlreadyLikedException;
import az.edadi.back.model.request.VoteRequestModel;
import az.edadi.back.repository.CommentRepository;
import az.edadi.back.repository.PostRepository;
import az.edadi.back.repository.VoteRepository;
import az.edadi.back.service.VoteService;
import az.edadi.back.utility.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public void addVote(VoteRequestModel voteRequestModel) {

        Vote vote = new Vote();
        vote.setDate(new Date());
        vote.setUser(new User(AuthUtil.getCurrentUserId()));
        switch (voteRequestModel.getType()) {
            case "post":
                vote = setPost(voteRequestModel.getId(), vote);
                break;
            case "comment":
                vote = setComment(voteRequestModel.getId(), vote);
                break;
        }

        if (getVote(voteRequestModel).isPresent())
            throw new PostAlreadyLikedException();

        voteRepository.save(vote);

    }

    Vote setPost(Long id, Vote vote) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        vote.setPost(post);
        return vote;
    }

    Vote setComment(Long id, Vote vote) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        vote.setComment(comment);
        return vote;
    }

    Optional<Vote> getVote(VoteRequestModel voteRequestModel) {
        switch (voteRequestModel.getType()) {
            case "post":
                return voteRepository.getPostVoteByIds(AuthUtil.getCurrentUserId(), voteRequestModel.getId());

            case "comment":
                return voteRepository.getCommentVoteByIds(AuthUtil.getCurrentUserId(), voteRequestModel.getId());
        }
        return Optional.empty();
    }

    @Override
    public void deleteVote(VoteRequestModel voteRequestModel) {
        Vote vote = getVote(voteRequestModel).orElseThrow(
                () -> new EntityNotFoundException()
        );
        voteRepository.delete(vote);
    }

}
