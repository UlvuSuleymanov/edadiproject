package az.edadi.back.repository;

import az.edadi.back.entity.university.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University,Long> {

//    boolean existsById(Long id);

    Optional<University> findByAbbr(String abbr);

//    @Override
//    Optional<University> findById(Long id);

//    Optional<University> findByName(String name);

}
