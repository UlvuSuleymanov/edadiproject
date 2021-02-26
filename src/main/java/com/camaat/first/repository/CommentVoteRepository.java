package com.camaat.first.repository;

 import com.camaat.first.entity.post.CommentVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentVoteRepository extends JpaRepository<CommentVote,Long> {
    @Query("select cv from CommentVote cv where cv.user.id = ?1 and cv.comment.id=?2")
    CommentVote getCommentVoteByIds(Long userid, Long postId);
}
