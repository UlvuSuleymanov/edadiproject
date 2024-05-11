package az.edadi.back.model.request;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.validation.PostType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestModel {

    @NotBlank
    @Size(min = 1, max = 700)
    private String text;

    @NotBlank
    private String type;

    @NotNull
    private Long id;

}
