package az.edadi.back.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArticleRequestModel {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String body;

    private List <String> tags;

}
