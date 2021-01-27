package com.camaat.first.repository;

import com.camaat.first.entity.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostVoteRepository extends JpaRepository<PostVote, Long> {

    @Query("select pv from PostVote pv where pv.user.id = ?1 and pv.post.id=?2")
     PostVote getPostVoteByIds(Long userOd,Long postId);

}
