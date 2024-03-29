package az.edadi.back.repository;

  import az.edadi.back.entity.app.FileItem;
  import org.springframework.data.jpa.repository.JpaRepository;
  import org.springframework.stereotype.Repository;

 import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<FileItem, UUID> {
}
