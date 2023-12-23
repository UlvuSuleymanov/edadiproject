package az.edadi.back.service.impl;

import az.edadi.back.constants.EntityType;
import az.edadi.back.entity.search.SearchItem;
import az.edadi.back.model.response.SearchRes;
import az.edadi.back.repository.*;
import az.edadi.back.service.ElasticsearchService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("dev")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ElasticsearchServiceImpl implements ElasticsearchService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final PostRepository postRepository;
    private final SearchRepository searchRepository;
    private final UniversityRepository universityRepository;
    private final SpecialityRepository specialityRepository;

    @Override
    @PostConstruct
    public void refreshElasticRepository() {
        searchRepository.deleteAll();

//        searchRepository.saveAll(
//                userRepository.findAll().stream().map(SearchItem::new).collect(Collectors.toList())
//        );

        searchRepository.saveAll(
                questionRepository.findAll().stream().map(SearchItem::new).collect(Collectors.toList())
        );

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
        Pageable pageable = PageRequest.of(page, 10);
        List<SearchItem> items = type.equals("all") ?
                searchRepository.findByTextContainingIgnoreCase(text.trim(), pageable)
                :
                searchRepository.findByTypeAndTextContainingIgnoreCase(type, text, pageable);

        return items.stream().map(SearchRes::new)
                .collect(Collectors.toList());

    }

}