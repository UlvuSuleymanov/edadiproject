package az.edadi.back.repository;


import az.edadi.back.entity.search.SearchItem;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Profile("prod")
@Repository
public interface SearchRepository extends ElasticsearchRepository<SearchItem,Long> {
    List<SearchItem>findByTextContainingIgnoreCase(String text, Pageable pageable);
    List<SearchItem>findByTypeAndTextContainingIgnoreCase(String type,String text, Pageable pageable);


}
