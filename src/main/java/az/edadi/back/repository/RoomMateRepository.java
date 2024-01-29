package az.edadi.back.repository;

import az.edadi.back.entity.roommate.Roommate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMateRepository extends JpaRepository<Roommate,Long> {
    @Query("SELECT rm FROM Roommate rm WHERE rm.region.id = :regionId")
    List<Roommate> getRoommatesByRegion(Long regionId, Pageable pageable);

    @Override
    Page<Roommate> findAll(Pageable pageable);
}
