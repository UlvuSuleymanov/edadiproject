package az.edadi.back.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RoommateRequestModel {
    @NotBlank
    private String title;
    private String info;
    private Integer amount;
    private String number;
    private Long roomSize;
    private Long region;
}
