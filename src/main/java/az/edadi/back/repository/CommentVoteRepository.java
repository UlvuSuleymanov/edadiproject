package az.edadi.back.repository;

 import az.edadi.back.entity.post.CommentVote;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

 import java.util.Optional;

@Repository
public interface CommentVoteRepository extends JpaRepository<CommentVote,Long> {
    @Query("select cv from CommentVote cv where cv.user.id = ?1 and cv.comment.id=?2")
    Optional<CommentVote> getCommentVoteByIds(Long userId, Long commentId);
}
