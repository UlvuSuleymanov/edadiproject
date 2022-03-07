package az.edadi.back.model.request;

import az.edadi.back.validation.PostType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
