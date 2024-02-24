package az.edadi.back.controller;

import az.edadi.back.model.response.SearchRes;
import az.edadi.back.service.SearchingService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/search")
public class SearchController {

    private final SearchingService searchingService;

    public SearchController(SearchingService searchingService) {
        this.searchingService = searchingService;
    }

    @GetMapping
    List<SearchRes> searchItem(@RequestParam @NotBlank String text,
                               @RequestParam(defaultValue = "all") String type,
                               @RequestParam(defaultValue = "1") int page) {
        return searchingService.search(text, type,page);

    }
}