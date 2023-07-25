package az.edadi.back.model.response;

import az.edadi.back.constants.AppConstants;
import az.edadi.back.entity.Article;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSummaryResponseModel {
    private String title;
    private String slug;
    private String description;
    private String imageUrl;
    private UserSummary author;
    private String birthDay;

    public ArticleSummaryResponseModel(Article article) {
        title = article.getTitle();
        slug = article.getSlug();
        description = article.getDescription();
        author = new UserSummary(article.getUser());
        imageUrl = AppConstants.ROOT_IMAGE_URL + AppConstants.BLOG_IMAGE_FOLDER + "/" + article.getCoverUrl();
        birthDay = DateUtil.getHowLongAgoString(article.getDate());
    }
}
