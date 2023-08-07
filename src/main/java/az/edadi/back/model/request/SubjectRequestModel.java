package az.edadi.back.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class SubjectRequestModel {
    @Size(min = 1,max = 255)
    private String name;

    @NotNull
    private Long specialityId;
}
