package az.edadi.back.service;

import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;

public interface ArticleService {


    ArticleResponseModel addArticle(ArticleRequestModel articleRequestModel);

    String createSlug(String title,Long id );

    Long getIdFromSlug(String slug);

    ArticleResponseModel getArticle(String slug);


}
