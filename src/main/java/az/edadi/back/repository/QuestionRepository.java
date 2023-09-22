package az.edadi.back.repository;

import az.edadi.back.entity.app.Question;
 import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("Select q from Question q where q.title like %?1%")
    List<Question> searchUniversityPostsLikeText(String text, Pageable pageable);
}
