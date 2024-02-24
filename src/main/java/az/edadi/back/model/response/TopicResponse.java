package az.edadi.back.model.response;

import az.edadi.back.entity.app.Topic;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.DateUtil;
import az.edadi.back.utility.SlugUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TopicResponse {
    private Long id;
    private String slug;
    private String title;
    private String date;
    private UserSummary user;

    public TopicResponse(Topic topic) {
        id = topic.getId();
        slug = SlugUtil.createSlug(topic.getTitle().toLowerCase(), topic.getId());
        title = topic.getTitle();
        date = DateUtil.getHowLongAgoString(topic.getDate());
        user=new UserSummary(topic.getUser());
    }
}
