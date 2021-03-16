package com.camaat.first.repository;

import com.camaat.first.entity.post.Post;
import com.camaat.first.model.response.SearchResultResponseModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

 import java.util.List;

@Repository
public interface PostRepository  extends JpaRepository<Post,Long> {

 @Query("SELECT p FROM Post p ORDER BY SIZE(p.comments) DESC")
 List<Post> getTopCommentPost(Pageable pageable);

 @Query("SELECT p FROM Post p ORDER BY SIZE(p.postVote) DESC")
 List<Post> getTopLikedPost(Pageable pageable);

 @Query("SELECT p FROM Post p where p.university.id=:id ORDER BY SIZE(p.comments) DESC")
 List<Post> getTopCommentUniversityPost(Long id,Pageable pageable);

 @Query("SELECT p FROM Post p  where p.university.id=?1 ORDER BY SIZE(p.postVote) DESC")
 List<Post> getTopLikedUniversityPost(Long id,Pageable pageable);




 @Query("Select new com.camaat.first.model.response.SearchResultResponseModel(p.id,p.postTitle) from Post p where p.postTitle like %:title%")
 List<SearchResultResponseModel> getPostLikeTitle(String title, Pageable pageable);



}
