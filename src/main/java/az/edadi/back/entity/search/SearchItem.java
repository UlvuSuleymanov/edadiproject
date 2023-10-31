package az.edadi.back.entity.search;

import az.edadi.back.constants.EntityType;
import az.edadi.back.entity.app.Question;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.University;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "search")
public class SearchItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String text;

    private Long entityId;

    private EntityType type;


    public SearchItem() {

    }

    public SearchItem(User user) {
        entityId = user.getId();
        type = EntityType.USER;
        text = user.getName() + " " + user.getUsername();
    }

    public SearchItem(Speciality speciality) {
        entityId = speciality.getId();
        type = EntityType.SPECIALITY;
        text = speciality.getName() + " " + speciality.getSpecialityCode();
    }

    public SearchItem(Post post) {
        entityId = post.getId();
        type = EntityType.POST;
        text = post.getText();
    }

    public SearchItem(Question question) {
        text = question.getTitle();
        type = EntityType.QUESTION;
        entityId = question.getId();
    }

    public SearchItem(University university) {
        entityId = university.getId();
        type = EntityType.UNIVERSITY;
        text = university.getAbbr()
                + " " + university.getAbbrAz()
                + " " + university.getInfo()
                + " " + university.getNameAz()
                + " " + university.getNameEn();
    }

}
