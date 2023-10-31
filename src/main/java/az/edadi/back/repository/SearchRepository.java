package az.edadi.back.repository;


import az.edadi.back.entity.search.SearchItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends ElasticsearchRepository<SearchItem,Long> {
    List<SearchItem>findByTextContainingIgnoreCase(String text, Pageable pageable);

}
