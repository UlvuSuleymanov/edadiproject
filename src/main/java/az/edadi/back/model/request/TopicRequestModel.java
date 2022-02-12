package az.edadi.back.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class TopicRequestModel {

    @NotBlank
    private String title;
}
