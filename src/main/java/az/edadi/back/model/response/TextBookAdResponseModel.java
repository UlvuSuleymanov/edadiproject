package az.edadi.back.model.response;

import az.edadi.back.entity.textbook.TextbookAd;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TextBookAdResponseModel {

    private Long id;
    private String name;
    private String about;
    private float price;
    private String date;
    private UserSummary user;

    public TextBookAdResponseModel(TextbookAd textbookAd) {
        id = textbookAd.getId();
        name = textbookAd.getName();
        about = textbookAd.getAbout();
        price = textbookAd.getPrice();
        date = DateUtil.getHowLongAgoString(textbookAd.getDate());
        user = new UserSummary(textbookAd.getUser());
    }
}
