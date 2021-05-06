package az.edadi.back.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagResponseModel {
    private  String tag;
    private Long id;
    private Integer point;

}
