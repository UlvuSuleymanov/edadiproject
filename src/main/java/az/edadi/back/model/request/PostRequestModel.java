package az.edadi.back.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestModel {
    @NotBlank
    private String text;
    private String type;
    private Long id;

}
