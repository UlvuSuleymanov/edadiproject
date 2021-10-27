package az.edadi.back.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SubjectRequestModel {
    @Size(min = 1,max = 255)
    private String name;

    @NotNull
    private Long specialityId;
}
