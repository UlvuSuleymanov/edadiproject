package az.edadi.back.controller;

import az.edadi.back.constants.PhotoEnum;
import az.edadi.back.constants.Property;
import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;
import az.edadi.back.model.response.ArticleSummaryResponseModel;
import az.edadi.back.model.response.SimpleImageResponse;
import az.edadi.back.service.ArticleService;
import az.edadi.back.utility.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/article")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    ResponseEntity addArticle(@ModelAttribute ArticleRequestModel articleRequestModel) throws IOException {

        ArticleResponseModel articleResponseModel = articleService.addArticle(articleRequestModel);
        return ResponseEntity.ok(articleResponseModel);

    }

    @PostMapping("/image")
    public SimpleImageResponse upload(@RequestBody MultipartFile upload) throws IOException {

        Long id = AuthUtil.getCurrentUserId();
        System.out.println(id);
        return articleService.addPhoto(upload);
    }

    @Autowired
    private Property property;

    @GetMapping
    ResponseEntity getArticles(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer size,
                               @RequestParam(defaultValue = "new") String sort) {

         List<ArticleSummaryResponseModel> articleResponseModel = articleService.getArticleList(page, size, sort);


        return ResponseEntity.ok(articleResponseModel);
    }


    @GetMapping("/{slug}")
    ResponseEntity getArticle(@PathVariable String slug) {

        ArticleResponseModel articleResponseModel = articleService.getArticle(slug);

        return ResponseEntity.ok(articleResponseModel);
    }


}
