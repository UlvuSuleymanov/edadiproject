package az.edadi.back.entity.search;

import az.edadi.back.entity.app.Question;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.University;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "search")
public class SearchItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String text;



    public SearchItem(){

    }
    public SearchItem(User user){
        text=user.getName()+" "+user.getUsername();
    }
    public SearchItem(Speciality speciality){
        text = speciality.getName()+" "+speciality.getSpecialityCode();
    }

    public SearchItem(Question question){
        text = question.getTitle();
    }

    public SearchItem(University university){
        text = university.getAbbr()
                +" "+university.getAbbrAz()
                +" "+university.getInfo()
                +" "+university.getNameAz()
                +" "+university.getNameEn();
    }

}
