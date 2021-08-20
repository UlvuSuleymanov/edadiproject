package az.edadi.back.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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
