package az.edadi.back.service;

import az.edadi.back.model.response.SearchRes;

import java.util.List;

public interface ElasticsearchService {

    void refreshElasticRepository();

    List<SearchRes> search(String text, String type,int page);

}
