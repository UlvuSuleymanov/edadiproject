package az.edadi.back.repository;

import az.edadi.back.entity.textbook.TextbookAd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextbookAdRepository extends JpaRepository<TextbookAd, Long> {
}
