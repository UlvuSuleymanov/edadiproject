package az.edadi.back.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniRequestModel {

    private String abbrName;
    private String name;
    private String info;
}
