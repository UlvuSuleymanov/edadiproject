package az.edadi.back.service.impl;

import az.edadi.back.constants.PhotoEnum;
import az.edadi.back.entity.Article;
import az.edadi.back.entity.User;
import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;
import az.edadi.back.model.response.ArticleSummaryResponseModel;
import az.edadi.back.model.response.SimpleImageResponse;
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
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
        article=articleRepository.save(article);
        article.setSlug(createSlug(article.getTitle(),article.getId()));
        article.setCoverUrl(getCoverImageName(articleRequestModel.getBody()));
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
        Element firstP = body.select("p").first();
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
    public SimpleImageResponse addPhoto(MultipartFile multipartFile) throws IOException {
        File file = fileService.convertMultiPartToFile(multipartFile);
        String name = UUID.randomUUID().toString();
        fileService.saveProfilePhoto(name,file,PhotoEnum.BLOG_IMAGE_FOLDER.getName());
        file.delete();
        SimpleImageResponse simpleImageResponse = new SimpleImageResponse();
        simpleImageResponse.setUrl(PhotoEnum.ROOT_PHOTO_URL.getName()+"blog/"+name);
        return simpleImageResponse;

    }

    @Override
    public Long getIdFromSlug(String slug) {
        String crumbs[]=slug.split("-");
        String id = crumbs[crumbs.length-1];
        return Long.valueOf(id);
    }

    @Override
    public String getCoverImageName(String html) {
        Document doc = Jsoup.parse(html);
        Optional<Element> image = Optional.ofNullable(doc.select("img").first());

        if(image.isPresent()) {
            String url= image.get().attr("src");
            return url.split("/")[url.split("/").length - 1];
        }
        return null;
    }


}
