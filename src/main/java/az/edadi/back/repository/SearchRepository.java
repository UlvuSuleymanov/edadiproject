package az.edadi.back.repository;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.search.SearchItem;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Lazy
public interface SearchRepository extends ElasticsearchRepository<SearchItem, Long> {
    List<SearchItem> findByTextContainingIgnoreCase(String text, Pageable pageable);
    List<SearchItem> findByTypeAndTextContainingIgnoreCase(EntityType type, String text, Pageable pageable);
}
