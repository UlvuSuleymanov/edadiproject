package az.edadi.back.model.response;

import az.edadi.back.entity.search.SearchItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRes {
    private Long id;
    private String type;
    private String text;

    public SearchRes(SearchItem item){
        id=item.getEntityId();
        type=item.getType().getType();
        text=item.getText();
    }
}
