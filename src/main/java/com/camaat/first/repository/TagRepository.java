package com.camaat.first.repository;

 import com.camaat.first.entity.Tag;
 import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {


    boolean existsByTag(String tag);
     Tag findByTag(String tag);

    @Query("SELECT t FROM Tag t ORDER BY SIZE(t.posts) DESC")
    List<Tag> getTopTags(Pageable pageable);


}
