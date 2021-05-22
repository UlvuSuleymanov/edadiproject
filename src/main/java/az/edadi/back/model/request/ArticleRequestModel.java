package az.edadi.back.model.request;

import lombok.Data;

import java.util.List;

@Data
public class ArticleRequestModel {
    private String title;
    private String body;
     private List <String> tags;
}
