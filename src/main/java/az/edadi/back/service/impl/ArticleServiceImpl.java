package az.edadi.back.service.impl;

import az.edadi.back.entity.Article;
import az.edadi.back.entity.User;
import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;
import az.edadi.back.model.response.ArticleSummaryResponseModel;
import az.edadi.back.repository.ArticleRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.ArticleService;
import az.edadi.back.utility.AuthUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(  UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleResponseModel addArticle(ArticleRequestModel articleRequestModel) {
        Long userId = AuthUtil.getCurrentUserId();
        User user = userRepository.getOne(userId);

        // set title & description
        Article article = parseHtml(articleRequestModel.getBody());
        article.setTitle(articleRequestModel.getTitle());
        article.setContent(articleRequestModel.getBody());
        article.setDate(new Date());
        article.setUser(user);
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
