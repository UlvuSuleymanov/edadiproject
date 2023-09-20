package az.edadi.back.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestModel implements Serializable {

    @NotBlank
    private String content;

    @NotNull
    private Long threadId;
}
