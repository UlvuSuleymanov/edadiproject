package az.edadi.back.controller;

import az.edadi.back.model.response.SearchRes;
import az.edadi.back.service.ElasticsearchService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchController {

    private final ElasticsearchService elasticsearchService;

    @GetMapping
    List<SearchRes> searchItem(@RequestParam @NotBlank String text,
                               @RequestParam(defaultValue = "all") String type,
                               @RequestParam(defaultValue = "1") int page) {
        return elasticsearchService.search(text, type,page);

    }
}