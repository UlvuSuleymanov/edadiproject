package az.edadi.back.model.response;

import az.edadi.back.entity.Article;
import az.edadi.back.model.UserSummary;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ArticleResponseModel {
    private Long id;
    private String title;
    private String body;
    private UserSummary author;
    private Date brithDay;
    private boolean isLiked;

    public ArticleResponseModel(Article article) {
        id = article.getId();
        title = article.getTitle();
        body = article.getContent();
        brithDay = article.getDate();
        author = new UserSummary(article.getUser());
    }
}
