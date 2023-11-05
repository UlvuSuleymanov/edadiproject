package az.edadi.back.model.response;

import az.edadi.back.constants.EntityType;
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
            case USER -> this.url = "/user/" + item.getId();
            case UNIVERSITY -> this.url = "/university/" + item.getId();
            case QUESTION -> this.url = "/question/" + item.getId();
            default -> this.url = "/";
        }
    }
}
