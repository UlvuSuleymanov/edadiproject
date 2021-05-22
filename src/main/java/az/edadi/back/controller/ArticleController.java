package az.edadi.back.controller;

import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;
import az.edadi.back.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/article")
public class ArticleController {
    private  final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    ResponseEntity addArticle(@RequestBody ArticleRequestModel articleRequestModel){

        ArticleResponseModel articleResponseModel = articleService.addArticle(articleRequestModel);


        return ResponseEntity.ok(articleResponseModel);

    }

    @GetMapping("/{slug}")
    ResponseEntity getArticle(@PathVariable String slug){

        ArticleResponseModel articleResponseModel = articleService.getArticle(slug);

        return ResponseEntity.ok(articleResponseModel);
    }


}
