package az.edadi.back.controller;

import az.edadi.back.entity.search.SearchItem;
import az.edadi.back.repository.SearchRepository;
import az.edadi.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;;

@RestController
@RequestMapping("api/search")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchController {

    private final SearchRepository searchRepository;
    private final UserRepository userRepository;

    @GetMapping("/{text}")
    String searchItem(@RequestParam String text){

        return searchRepository.findByTextContainingIgnoreCase(text).toString();

     }
}
