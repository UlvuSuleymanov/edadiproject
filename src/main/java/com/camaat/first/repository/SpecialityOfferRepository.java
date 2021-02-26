package com.camaat.first.repository;


import com.camaat.first.entity.university.SpecialityOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityOfferRepository extends JpaRepository<SpecialityOffer,Long> {
}
