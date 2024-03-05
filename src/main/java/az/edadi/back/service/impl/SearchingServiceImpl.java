package az.edadi.back.service.impl;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.search.SearchItem;
import az.edadi.back.model.response.SearchRes;
import az.edadi.back.repository.*;
import az.edadi.back.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("elasticsearch")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchingServiceImpl implements SearchService {

    private final SearchRepository searchRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UniversityRepository universityRepository;
    private final SpecialityRepository specialityRepository;


    public void refreshElasticRepository() {
        searchRepository.deleteAll();

        searchRepository.saveAll(
                userRepository.findAll().stream().map(SearchItem::new).collect(Collectors.toList())
        );

//        searchRepository.saveAll(
//                questionRepository.findAll().stream().map(SearchItem::new).collect(Collectors.toList())
//        );

//        searchRepository.saveAll(
//                postRepository.findAll().stream().map(SearchItem::new).collect(Collectors.toList())
//        );

        searchRepository.saveAll(
                universityRepository.findAll().stream().map(SearchItem::new).collect(Collectors.toList())
        );

        searchRepository.saveAll(
                specialityRepository.findAll().stream().map(SearchItem::new).collect(Collectors.toList())
        );

    }

    @Override
    public List<SearchRes> search(String text, String type, int page) {

        Pageable pageable = PageRequest.of(page - 1, 10);
        EntityType entityType = EntityType.of(type);
        List<SearchItem> items;
        if (entityType.equals(EntityType.All))
            items = searchRepository.findByTextContainingIgnoreCase(text.trim(), pageable);
        else
            items = searchRepository.findByTypeAndTextContainingIgnoreCase(entityType,text, pageable);

        return items.stream().map(SearchRes::new)
                .collect(Collectors.toList());

    }


}