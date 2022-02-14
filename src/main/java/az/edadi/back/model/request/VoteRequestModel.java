package az.edadi.back.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteRequestModel {
    private String type;
    private Long id;
}
