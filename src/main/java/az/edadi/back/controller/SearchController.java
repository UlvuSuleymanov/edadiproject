package az.edadi.back.controller;

import az.edadi.back.model.response.SearchRes;
import az.edadi.back.service.ElasticsearchService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/search")
public class SearchController {

    private final ElasticsearchService elasticsearchService;

    public SearchController(ElasticsearchService elasticsearchService) {
        this.elasticsearchService = elasticsearchService;
    }

    @GetMapping
    List<SearchRes> searchItem(@RequestParam @NotBlank String text,
                               @RequestParam(defaultValue = "all") String type,
                               @RequestParam(defaultValue = "1") int page) {
        return elasticsearchService.search(text, type,page);

    }
}