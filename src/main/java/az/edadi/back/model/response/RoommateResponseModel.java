package az.edadi.back.model.response;

import az.edadi.back.entity.roommate.RoommateAd;
import az.edadi.back.model.MediaAuthor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@Data
public class RoommateResponseModel {
    private Long id;
    private String title;
    private String info;
    private MediaAuthor author;
    private String region;
    private String regionId;
    private Date date;
    private Integer amount;
    private Long roomSize;

    public RoommateResponseModel(RoommateAd roommateAd){
        id=roommateAd.getId();
        title=roommateAd.getTitle();
        info=roommateAd.getInfo();
        author= new MediaAuthor(roommateAd.getUser());
        region=roommateAd.getRegion().getName();
        regionId=roommateAd.getRegion().getId().toString();
        date=roommateAd.getDate();
        amount=roommateAd.getAmount();
        roomSize=roommateAd.getSize();

    }

}
