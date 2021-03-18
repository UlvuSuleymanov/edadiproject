package com.camaat.first.repository;

import com.camaat.first.entity.post.Comment;
import com.camaat.first.entity.post.Post;
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
