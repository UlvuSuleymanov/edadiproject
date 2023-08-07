package az.edadi.back.model.request;

import az.edadi.back.validation.PostType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class GetPostRequestModel {

    @NotBlank
    @PostType
    private String type;

    @NotNull
    private Long id;

    @Min(1)
    private int page;

    @NotBlank
    private String sort;

    @NotNull
    private String searchText;

    private boolean asc;
}
