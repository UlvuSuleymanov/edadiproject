package com.camaat.first.repository;

 import com.camaat.first.entity.university.Speciality;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality,Long> {
}
