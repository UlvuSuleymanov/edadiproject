package az.edadi.back.model.response;

import az.edadi.back.entity.Topic;
import az.edadi.back.utility.SlugUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TopicResponseModel {
    private String slug;
    private String title;
    private Date date;

    public TopicResponseModel(Topic topic){
        slug = SlugUtil.createSlug(topic.getTitle().toLowerCase(),topic.getId());
        title=topic.getTitle();
        date=topic.getDate();
    }
}
