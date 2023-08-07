package az.edadi.back.model.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetCommentListRequestParamsModel {

    @NotNull
    private Long postId;

    @Min(0)
    private int page;

    @NotBlank
    private String sort;

    private boolean asc;
}
