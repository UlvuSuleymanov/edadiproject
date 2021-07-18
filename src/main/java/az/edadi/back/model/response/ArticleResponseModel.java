package az.edadi.back.model.response;

import az.edadi.back.entity.Article;
import az.edadi.back.utility.PhotoUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class ArticleResponseModel {
    private Long id;
    private String title;
    private String body;
    private String authorName;
    private String authorUsername;
    private String authorPhotoUrl;
    private Date brithDay;
    private boolean isLiked;

    public   ArticleResponseModel(Article article){
        id=article.getId();
        title=article.getTitle();
        body=article.getContent();
        authorUsername=article.getUser().getUsername();
        authorName=article.getUser().getName();
        authorPhotoUrl= PhotoUtil.getFullPhotoUrl(article.getUser().getPhotoUrl());
        brithDay=article.getDate();
    }
}
