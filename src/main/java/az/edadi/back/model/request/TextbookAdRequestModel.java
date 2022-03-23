package az.edadi.back.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextbookAdRequestModel {

    @NotBlank
    @Max(255)
    private String name;

    @Max(255)
    private String about;

    @NotNull
    private float price;

    @NotNull
    private Long type;

    private Long specialityId;
}
