package az.edadi.back.repository;

import az.edadi.back.entity.post.Post;
import az.edadi.back.model.response.SearchResultResponseModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

 import java.util.List;

@Repository
public interface PostRepository  extends JpaRepository<Post,Long> {

 @Query("SELECT p FROM Post p ORDER BY SIZE(p.comments) DESC")
 List<Post> getTopCommentPost(Pageable pageable);

 @Query("SELECT p FROM Post p ORDER BY SIZE(p.votes) DESC")
 List<Post> getTopLikedPost(Pageable pageable);

 @Query("SELECT p FROM Post p where p.university.id=:id ORDER BY SIZE(p.comments) DESC")
 List<Post> getTopCommentUniversityPost(Long id,Pageable pageable);

 @Query("SELECT p FROM Post p  where p.university.id=?1 ORDER BY SIZE(p.votes) DESC")
 List<Post> getTopLikedUniversityPost(Long id,Pageable pageable);


 @Query("SELECT p FROM Post p  where p.speciality.specialityCode=?1")
 List<Post> getSpecialityPosts(Long code, Pageable pageable);






 @Query("Select p from Post p where p.university.id=?1 and p.text like %?2%")
 List<Post> searchUniversityPostsLikeText( Long id, String text, Pageable pageable);

 @Query("Select p from Post p where p.speciality.id=?1 and p.text like %?2%")
 List<Post> searchSpecialityPostsLikeText(Long id, String text, Pageable pageable);

 @Query("Select p from Post p where p.topic.id=?1 and p.text like %?2%")
 List<Post> searchTopicPostsLikeText(Long id, String text, Pageable pageable);


}
