package az.edadi.back.service.impl;

import az.edadi.back.constants.PhotoEnum;
import az.edadi.back.entity.Article;
import az.edadi.back.entity.User;
import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;
import az.edadi.back.model.response.ArticleSummaryResponseModel;
import az.edadi.back.repository.ArticleRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.ArticleService;
import az.edadi.back.service.FileService;
import az.edadi.back.service.ImageService;
import az.edadi.back.utility.AuthUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ImageService imageService;
    private final FileService fileService;
    @Autowired
    public ArticleServiceImpl(UserRepository userRepository, ArticleRepository articleRepository, ImageService imageService, FileService fileService) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.imageService = imageService;
        this.fileService = fileService;
    }

    @Override
    public ArticleResponseModel addArticle(ArticleRequestModel articleRequestModel) throws IOException {
        Long userId = AuthUtil.getCurrentUserId();
        User user = new User();
        user.setId(userId);

        Article article = new Article(articleRequestModel);
        article.setUser(user);
        article.setCoverUrl(setPhoto(articleRequestModel));
        article=articleRepository.save(article);
        article.setSlug(createSlug(article.getTitle(),article.getId()));

        return new ArticleResponseModel(articleRepository.save(article));
     }

    @Override
    public ArticleResponseModel getArticle(String slug) {
        Long id = getIdFromSlug(slug);
        Article article = articleRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException());

        return new ArticleResponseModel(article);
    }

    @Override
    public List<ArticleSummaryResponseModel> getArticleList(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size);

        List<Article> articles = articleRepository.getArticles(pageable);
        return
                articles !=null ?
                        articles.stream().map(
                              article -> new ArticleSummaryResponseModel(article))
                .collect(Collectors.toList())

        : Collections.emptyList();
    }

    @Override
    @Deprecated
    public Article parseHtml(String articleText) {
        Article article = new Article();
        Document body = Jsoup.parse(articleText);
//        Element title = body.select("h1").first();
        Element firstP = body.select("p").first();

//        article.setTitle(title.text());
        article.setDescription(firstP.text());
        return article;
    }

    @Override
    public String setPhoto(ArticleRequestModel articleRequestModel) throws IOException {
        if(articleRequestModel.getMultipartFile()!=null){

            File file = fileService.convertMultiPartToFile(articleRequestModel.getMultipartFile());
            String uuid= UUID.randomUUID().toString();
            fileService.save(uuid,file);
            file.delete();
            return uuid;
        }
        else
            return PhotoEnum.ARTICLE_DEFAULT_PHOTO.getName();


    }

    @Override
    public String createSlug(String title,Long id) {
     title = title.trim().replaceAll("\\s{2,}", "-");
     title=title.replace(" ","-");
     return title+"-"+id;
    }

    @Override
    public Long getIdFromSlug(String slug) {
        String crumbs[]=slug.split("-");
        String id = crumbs[crumbs.length-1];
        return Long.valueOf(id);
    }


}
