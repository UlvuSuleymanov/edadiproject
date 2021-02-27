package com.camaat.first.repository;

import com.camaat.first.entity.university.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality,Long> {
//     List<Speciality> findByUniversityIsNull();
//     List<Speciality> findByUniversityId(Long id);
     boolean existsById(Long id);

//     List<Speciality> getSpecialityList();


     Optional<Speciality> findById(Long id);
}
