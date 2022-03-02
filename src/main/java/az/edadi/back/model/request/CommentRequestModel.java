package az.edadi.back.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CommentRequestModel {

    @NotNull
    private Long postId;

    @NotBlank
    @Size(min = 1, max = 500)
    private String text;
 }
