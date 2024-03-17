package az.edadi.back.repository;

import az.edadi.back.entity.app.Reels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReelsRepository extends JpaRepository<Reels,Long> {
}
