package com.camaat.first.repository;

import com.camaat.first.entity.university.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University,Long> {

    boolean existsById(Long id);

    @Override
    Optional<University> findById(Long id);
}
