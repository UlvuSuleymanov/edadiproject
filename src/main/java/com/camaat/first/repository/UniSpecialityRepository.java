package com.camaat.first.repository;

import com.camaat.first.entity.university.UniSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniSpecialityRepository extends JpaRepository<UniSpeciality,Long> {
}
