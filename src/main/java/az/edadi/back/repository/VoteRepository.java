package az.edadi.back.repository;

import az.edadi.back.entity.post.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("select v from Vote v where v.user.id = ?1 and v.post.id=?2")
    Optional<Vote> getPostVoteByIds(Long userId, Long postId);

    @Query("select v from Vote v where v.user.id = ?1 and v.comment.id=?2")
    Optional<Vote> getCommentVoteByIds(Long userId, Long commentId);

}
