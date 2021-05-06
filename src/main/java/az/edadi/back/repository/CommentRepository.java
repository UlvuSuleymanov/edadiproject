package az.edadi.back.repository;

import az.edadi.back.entity.post.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

  import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
     List<Comment> findByPost_id(Long postId, Pageable pageable);

    @Query("SELECT c FROM Comment c where c.user.id=:id ORDER BY SIZE(c.commentVotes) DESC ")
    List<Comment> getTopLikedComment(Long id,Pageable pageable);
}
