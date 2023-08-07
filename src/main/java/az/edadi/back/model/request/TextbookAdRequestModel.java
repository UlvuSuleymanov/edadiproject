package az.edadi.back.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
