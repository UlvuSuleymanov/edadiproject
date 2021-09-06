package az.edadi.back.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestModel {
    @NotBlank
    private String text;
    @NotBlank
    private String type;
    @NotNull
    private Long id;

}
