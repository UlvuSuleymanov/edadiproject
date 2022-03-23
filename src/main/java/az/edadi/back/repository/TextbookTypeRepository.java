package az.edadi.back.repository;

import az.edadi.back.entity.textbook.TextBookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextbookTypeRepository extends JpaRepository<TextBookType, Long> {
}
