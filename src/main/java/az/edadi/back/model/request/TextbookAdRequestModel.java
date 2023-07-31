package az.edadi.back.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextbookAdRequestModel {

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String about;

    @NotNull
    @PositiveOrZero
    private float price;

    @NotNull
    private Long type;

    @NotNull
    private Long specialityId;
}
