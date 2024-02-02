package az.edadi.back.repository;

import az.edadi.back.entity.app.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
    @Query("SELECT f FROM File f WHERE f.id IN :ids")
    List<File> findByIds(List<UUID> ids);
}
