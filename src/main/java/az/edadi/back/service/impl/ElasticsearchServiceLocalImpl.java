package az.edadi.back.service.impl;

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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ElasticsearchServiceLocalImpl implements ElasticsearchService {


    @Override
    public void refreshElasticRepository() {

    }

    @Override
    public List<SearchRes> search(String text, String type, int page) {
        return null;
    }
}