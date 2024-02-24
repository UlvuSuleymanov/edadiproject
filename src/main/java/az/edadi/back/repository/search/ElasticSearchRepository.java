package az.edadi.back.repository.search;

import az.edadi.back.entity.search.SearchItem;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
 import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Profile("prod")
public interface ElasticSearchRepository extends SearchRepository {

}
