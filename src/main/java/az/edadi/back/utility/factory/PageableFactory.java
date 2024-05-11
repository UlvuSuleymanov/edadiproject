package az.edadi.back.utility.factory;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableFactory {
    public static Pageable getDateSortedPageable(int page) {
        return PageRequest.of(page, 20, Sort.by("dateCreated").descending());
    }

}
