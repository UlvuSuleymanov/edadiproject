package az.edadi.back.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CommentRequestModel {
    @NotBlank
    @Size(min = 1, max = 400)
    private String commentText;
 }
