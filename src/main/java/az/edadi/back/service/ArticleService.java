package az.edadi.back.service;

import az.edadi.back.entity.app.Article;
import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;
import az.edadi.back.model.response.ArticleSummaryResponseModel;
import az.edadi.back.model.response.SimpleImageResponse;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ArticleService {

    ArticleResponseModel addArticle(ArticleRequestModel articleRequestModel) throws IOException;

    SimpleImageResponse addPhoto(MultipartFile multipartFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    String getCoverImageName(String html);

    ArticleResponseModel getArticle(String slug);

    List<ArticleSummaryResponseModel> getArticleList(int page, int size, String sort);

    Article parseHtml(String articleText);

}
