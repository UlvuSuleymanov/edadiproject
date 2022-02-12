package az.edadi.back.model.response;

import az.edadi.back.entity.Topic;
import az.edadi.back.utility.SlugUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TopicResponseModel {
    private Long id;
    private String slug;
    private String title;
    private Date date;

    public TopicResponseModel(Topic topic){
        id=topic.getId();
        slug = SlugUtil.createSlug(topic.getTitle().toLowerCase(),topic.getId());
        title=topic.getTitle();
        date=topic.getDate();
    }
}
