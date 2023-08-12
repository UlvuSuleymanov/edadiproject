package az.edadi.back.model.request;

import jakarta.validation.constraints.Max;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class RoommateRequestModel {
    @Length(min = 1,max = 255)
    private String info;

    @Max(value = 100000)
    private Integer amount;

    @Length(min = 1,max = 255)
    private String contact;

    private Long region;
}
