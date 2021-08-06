package az.edadi.back.repository;

import az.edadi.back.entity.roommate.RoommateAd;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMateRepository extends JpaRepository<RoommateAd,Long> {
    @Query("SELECT rm FROM RoommateAd rm WHERE rm.region.id = :regionId")
    List<RoommateAd> getRoommatesByRegion(Long regionId,Pageable pageable);
}
