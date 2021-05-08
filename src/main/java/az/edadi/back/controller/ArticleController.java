package az.edadi.back.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/article")
public class ArticleController {

    @GetMapping("/{slug}")
    ResponseEntity getArticle(@PathVariable String slug){



        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}
