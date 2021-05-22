package az.edadi.back.service.impl;

import az.edadi.back.entity.Article;
import az.edadi.back.entity.User;
import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;
import az.edadi.back.repository.ArticleRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.ArticleService;
import az.edadi.back.utility.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

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
        Article article = new Article();
        User user = userRepository.getOne(userId);
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
