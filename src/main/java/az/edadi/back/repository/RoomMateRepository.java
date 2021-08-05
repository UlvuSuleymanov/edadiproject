package az.edadi.back.repository;

import az.edadi.back.entity.roommate.RoommateAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomMateRepository extends JpaRepository<RoommateAd,Long> {
}
