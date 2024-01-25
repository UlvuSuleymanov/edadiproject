package az.edadi.back.service.impl;

import az.edadi.back.constants.AppConstants;
import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.entity.app.Article;
import az.edadi.back.entity.auth.User;
import az.edadi.back.model.request.ArticleRequestModel;
import az.edadi.back.model.response.ArticleResponseModel;
import az.edadi.back.model.response.ArticleSummaryResponseModel;
import az.edadi.back.model.response.SimpleImageResponse;
import az.edadi.back.repository.ArticleRepository;
import az.edadi.back.repository.UserEventsRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.ArticleService;
import az.edadi.back.service.FileIOService;
import az.edadi.back.service.ImageService;
import az.edadi.back.utility.AuthUtil;
import az.edadi.back.utility.SlugUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleServiceImpl implements ArticleService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ImageService imageService;
    private final FileIOService fileIOService;
    private final UserEventsRepository userEventsRepository;

    @Override
    public ArticleResponseModel addArticle(ArticleRequestModel articleRequestModel) throws IOException {
        userEventsRepository.check(UserEvent.ADD_ARTICLE);
        Long userId = AuthUtil.getCurrentUserId();
        User user = new User();
        user.setId(userId);

        Article article = new Article(articleRequestModel);
        article.setUser(user);
        article = articleRepository.save(article);
        article.setSlug(SlugUtil.createSlug(article.getTitle(), article.getId()));
        article.setCoverUrl(getCoverImageName(articleRequestModel.getBody()));
        return new ArticleResponseModel(articleRepository.save(article));
    }

    @Override
    public ArticleResponseModel getArticle(String slug) {
        Long id = SlugUtil.getId(slug);
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return new ArticleResponseModel(article);
    }

    @Override
    public List<ArticleSummaryResponseModel> getArticleList(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size);

        List<Article> articles = articleRepository.getArticles(pageable);
        return
                articles != null ?
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
    public SimpleImageResponse addPhoto(MultipartFile multipartFile) throws IOException {
        File file = imageService.convertMultiPartToFile(multipartFile);
        String name = UUID.randomUUID().toString();
        fileIOService.saveFile(name, file, AppConstants.BLOG_IMAGE_FOLDER);
        file.delete();
        SimpleImageResponse simpleImageResponse = new SimpleImageResponse();
        simpleImageResponse.setUrl(AppConstants.ROOT_IMAGE_URL + AppConstants.BLOG_IMAGE_FOLDER + "/" + name);
        return simpleImageResponse;

    }


    @Override
    public String getCoverImageName(String html) {
        Document doc = Jsoup.parse(html);
        Optional<Element> image = Optional.ofNullable(doc.select("img").first());

        if (image.isPresent()) {
            String url = image.get().attr("src");
            return url.split("/")[url.split("/").length - 1];
        }
        return null;
    }


}
