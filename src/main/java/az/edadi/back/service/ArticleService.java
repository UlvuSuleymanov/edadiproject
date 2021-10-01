package az.edadi.back.service;

import az.edadi.back.entity.Article;
import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;
import az.edadi.back.model.response.ArticleSummaryResponseModel;
import az.edadi.back.model.response.SimpleImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArticleService {


    ArticleResponseModel addArticle(ArticleRequestModel articleRequestModel) throws IOException;

    String createSlug(String title,Long id );

    SimpleImageResponse addPhoto(MultipartFile multipartFile) throws IOException;

    Long getIdFromSlug(String slug);

    String getCoverImageName(String html);

    ArticleResponseModel getArticle(String slug);

    List<ArticleSummaryResponseModel> getArticleList(int page, int size, String sort);

    Article parseHtml(String articleText);
}
