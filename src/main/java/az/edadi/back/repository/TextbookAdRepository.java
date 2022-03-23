package az.edadi.back.repository;

import az.edadi.back.entity.textbook.TextbookAd;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextbookAdRepository extends JpaRepository<TextbookAd, Long> {

    @Query("SELECT ta FROM TextbookAd ta WHERE ta.type.id = :type")
    List<TextbookAd> getTextbooks(Long type, Pageable pageable);

    @Query("SELECT ta FROM TextbookAd ta WHERE ta.type.id = :type and ta.specialityId.id = :specialityId")
    List<TextbookAd> getTextbooks(Long type,Long specialityId, Pageable pageable);
}
