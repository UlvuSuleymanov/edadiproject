package az.edadi.back.repository;

import az.edadi.back.entity.post.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostVoteRepository extends JpaRepository<PostVote, Long> {

    @Query("select pv from PostVote pv where pv.user.id = ?1 and pv.post.id=?2")
     PostVote getPostVoteByIds(Long userOd,Long postId);

}
