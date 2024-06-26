package az.edadi.back.model.request;

import az.edadi.back.constants.type.PostType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class GetPostRequestModel {

    @NotNull
    private PostType type;

    @NotNull
    private Long id;

    @Min(1)
    private int page;

    @NotBlank
    private String sort;

    private String searchText;

    private boolean asc;
    public void setType(String type) {
        this.type = PostType.valueOf(type.toUpperCase());
    }
}
