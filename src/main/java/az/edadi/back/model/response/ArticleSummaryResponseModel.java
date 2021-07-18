package az.edadi.back.model.response;

import az.edadi.back.entity.Article;
import az.edadi.back.model.MediaAuthor;
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
    private  String title;
    private  String slug;
    private  String description;
    private MediaAuthor author;
    private Date date;

    public ArticleSummaryResponseModel(Article article){
        title=article.getTitle();
        slug=article.getSlug();
        description=article.getDescription();
        date =article.getDate();
        author=new MediaAuthor(article.getUser());
    }
}
