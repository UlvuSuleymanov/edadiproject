package az.edadi.back.model.response;

import az.edadi.back.entity.textbook.TextbookAd;
import az.edadi.back.model.UserSummary;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class TextBookAdResponseModel {

    private Long id;
    private String name;
    private String about;
    private float price;
    private Date date;
    private UserSummary user;

    public TextBookAdResponseModel(TextbookAd textbookAd) {
        id = textbookAd.getId();
        name = textbookAd.getName();
        about = textbookAd.getAbout();
        price = textbookAd.getPrice();
        date = textbookAd.getDate();
        user = new UserSummary(textbookAd.getUser());
    }
}
