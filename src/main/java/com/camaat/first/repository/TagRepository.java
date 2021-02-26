package com.camaat.first.repository;

 import com.camaat.first.entity.post.Tag;
 import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 import org.springframework.stereotype.Repository;

 import java.util.List;
@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {


    boolean existsByTag(String tag);
     Tag findByTag(String tag);

    @Query("SELECT t FROM Tag t ORDER BY SIZE(t.posts) DESC")
    List<Tag> getTopTags(Pageable pageable);


}
