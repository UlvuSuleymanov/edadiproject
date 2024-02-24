package az.edadi.back.service.impl;

import az.edadi.back.model.response.SearchRes;
import az.edadi.back.service.SearchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Profile("!elasticsearch")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchingServiceStatic implements SearchingService {

    @Override
    public void refreshElasticRepository() {
    }

    @Override
    public List<SearchRes> search(String text, String type, int page) {
        return Collections.emptyList();
    }
}