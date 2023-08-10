package az.edadi.back.model.response;

import az.edadi.back.entity.Question;
import az.edadi.back.utility.DateUtil;
import az.edadi.back.utility.SlugUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionResponseModel {
    private Long id;
    private String slug;
    private String title;
    private String date;

    public QuestionResponseModel(Question question) {
        id = question.getId();
        slug = SlugUtil.createSlug(question.getTitle().toLowerCase(), question.getId());
        title = question.getTitle();
        date = DateUtil.getHowLongAgoString(question.getDate());
    }
}
