package az.edadi.back.controller;

import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;
import az.edadi.back.model.response.ArticleSummaryResponseModel;
import az.edadi.back.model.response.SimpleImageResponse;
import az.edadi.back.service.ArticleService;
import az.edadi.back.utility.AuthUtil;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("api/article")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    ResponseEntity addArticle(@ModelAttribute ArticleRequestModel articleRequestModel) throws IOException {
        ArticleResponseModel articleResponseModel = articleService.addArticle(articleRequestModel);
        return ResponseEntity.ok(articleResponseModel);

    }

    @PostMapping("/image")
    public SimpleImageResponse upload(@RequestBody MultipartFile upload) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        Long id = AuthUtil.getCurrentUserId();
        return articleService.addPhoto(upload);
    }


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
