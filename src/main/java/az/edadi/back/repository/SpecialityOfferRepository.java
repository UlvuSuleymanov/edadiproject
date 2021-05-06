package az.edadi.back.repository;


import az.edadi.back.entity.university.SpecialityOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityOfferRepository extends JpaRepository<SpecialityOffer,Long> {
}
