package az.edadi.back.model.request;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
