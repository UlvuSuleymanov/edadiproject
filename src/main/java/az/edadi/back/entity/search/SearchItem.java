package az.edadi.back.entity.search;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.app.Topic;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.University;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@Document(indexName = "search")
public class SearchItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String text;

    private String entityId;

    private EntityType type;

    private Date date;


    public SearchItem() {

    }

    public SearchItem(User user) {
        entityId = user.getUsername();
        type = EntityType.USER;
        text = user.getName() + " " + user.getUsername();
    }

    public SearchItem(Speciality speciality) {
        entityId = speciality.getId().toString();
        type = EntityType.SPECIALITY;
        text = speciality.getName() + " " + speciality.getSpecialityCode();
    }

    public SearchItem(Post post) {
        entityId = post.getId().toString();
        type = EntityType.POST;
        text = post.getText();
    }

    public SearchItem(Topic topic) {
        text = topic.getTitle();
        type = EntityType.QUESTION;
        entityId = topic.getId().toString();
    }

    public SearchItem(University university) {
        entityId = university.getId().toString();
        type = EntityType.UNIVERSITY;
        text = university.getAbbr()
                + " " + university.getAbbrAz()
                + " " + university.getInfo()
                + " " + university.getNameAz()
                + " " + university.getNameEn();
    }



}
