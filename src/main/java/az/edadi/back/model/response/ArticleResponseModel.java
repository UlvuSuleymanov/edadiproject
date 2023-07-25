package az.edadi.back.model.response;

import az.edadi.back.entity.Article;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleResponseModel {
    private Long id;
    private String title;
    private String body;
    private UserSummary author;
    private String brithDay;
    private boolean isLiked;

    public ArticleResponseModel(Article article) {
        id = article.getId();
        title = article.getTitle();
        body = article.getContent();
        brithDay = DateUtil.getHowLongAgoString(article.getDate());
        author = new UserSummary(article.getUser());
    }
}
