package az.edadi.back.model.response;

import az.edadi.back.entity.Topic;
import az.edadi.back.utility.DateUtil;
import az.edadi.back.utility.SlugUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TopicResponseModel {
    private Long id;
    private String slug;
    private String title;
    private String date;

    public TopicResponseModel(Topic topic) {
        id = topic.getId();
        slug = SlugUtil.createSlug(topic.getTitle().toLowerCase(), topic.getId());
        title = topic.getTitle();
        date = DateUtil.getHowLongAgoString(topic.getDate());
    }
}
