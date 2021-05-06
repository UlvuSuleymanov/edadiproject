package az.edadi.back.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestModel {
    private String text;
    private String type;
    private Long id;

}
