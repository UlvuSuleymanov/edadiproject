package az.edadi.back.model.request;

import az.edadi.back.validation.VoteType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequestModel {

    @NotBlank
    @VoteType
    private String type;

    @NotNull
    private Long id;
}
