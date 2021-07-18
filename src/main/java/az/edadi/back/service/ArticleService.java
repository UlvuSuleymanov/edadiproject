package az.edadi.back.service;

import az.edadi.back.entity.Article;
import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;
import az.edadi.back.model.response.ArticleSummaryResponseModel;

import java.util.List;

public interface ArticleService {


    ArticleResponseModel addArticle(ArticleRequestModel articleRequestModel);

    String createSlug(String title,Long id );

    Long getIdFromSlug(String slug);

    ArticleResponseModel getArticle(String slug);

    List<ArticleSummaryResponseModel> getArticleList(int page, int size, String sort);

    Article parseHtml(String articleText);
}
