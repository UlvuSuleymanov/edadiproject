package az.edadi.back.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TopicRequestModel {

    @NotBlank
    private String title;
}
