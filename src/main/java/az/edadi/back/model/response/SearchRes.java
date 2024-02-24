package az.edadi.back.model.response;

import az.edadi.back.entity.search.SearchItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRes {
    private String id;
    private String type;
    private String text;
    private String url;

    public SearchRes(SearchItem item){
        id=item.getEntityId();
        type=item.getType().getType();
        text=item.getText();
        switch (item.getType()) {
            case USER -> this.url = "/user/" + item.getEntityId();
            case UNIVERSITY -> this.url = "/university/" + item.getEntityId();
            case QUESTION -> this.url = "/question/" + item.getEntityId();
            default -> this.url = "/";
        }
    }
}
