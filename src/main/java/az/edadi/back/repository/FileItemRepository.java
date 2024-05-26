package az.edadi.back.repository;

import az.edadi.back.entity.app.FileItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileItemRepository extends JpaRepository<FileItem, Long> {
    @Query("SELECT f FROM FileItem f WHERE f.id IN :ids")
    List<FileItem> findByIds(List<UUID> ids);
    Optional<FileItem> findByUuid(UUID uuid);
}
