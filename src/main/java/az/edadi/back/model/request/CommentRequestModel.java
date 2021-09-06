package az.edadi.back.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CommentRequestModel {
    @NotBlank
    private String commentText;
 }
