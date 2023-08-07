package az.edadi.back.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;



@Data
@NoArgsConstructor
public class CommentRequestModel {

    @NotNull
    private Long postId;

    @NotBlank
    @Size(min = 1, max = 500)
    private String text;
 }
