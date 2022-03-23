package az.edadi.back.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextbookAdRequestParamsModel {
    private int page;
    private boolean asc;
    private Long type;
    private Long specialityId;
}
