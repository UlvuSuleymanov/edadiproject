package az.edadi.back.repository;

import az.edadi.back.entity.app.SharedStudyMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyMaterialRepository extends JpaRepository<SharedStudyMaterial,Long> {
}
