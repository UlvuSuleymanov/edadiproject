package az.edadi.back.entity.textbook;

import az.edadi.back.entity.User;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.model.request.TextbookAdRequestModel;
import az.edadi.back.utility.AuthUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TextbookAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String about;

    private Date date;

    private float price;

    @ManyToOne(fetch = FetchType.EAGER)
    private TextBookType type;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Speciality speciality;


    public static TextbookAd from(TextbookAdRequestModel textbookAdRequestModel) {
        TextbookAd textbookAd = new TextbookAd();
        textbookAd.setUser(new User(AuthUtil.getCurrentUserId()));
        textbookAd.setAbout(textbookAdRequestModel.getAbout());
        textbookAd.setName(textbookAdRequestModel.getName());
        textbookAd.setDate(new Date());
        textbookAd.setPrice(textbookAdRequestModel.getPrice());
        return textbookAd;
    }
}
