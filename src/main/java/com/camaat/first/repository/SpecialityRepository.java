package com.camaat.first.repository;

import com.camaat.first.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality,Long> {
     List<Speciality> findByUniversityIsNull();
}
