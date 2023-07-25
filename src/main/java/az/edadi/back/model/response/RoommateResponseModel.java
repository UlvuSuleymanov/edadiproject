package az.edadi.back.model.response;

import az.edadi.back.entity.roommate.RoommateAd;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RoommateResponseModel {
    private Long id;
    private String title;
    private String info;
    private UserSummary author;
    private String region;
    private String regionId;
    private String date;
    private Integer amount;
    private Long roomSize;
    private String contact;

    public RoommateResponseModel(RoommateAd roommateAd) {
        id = roommateAd.getId();
        title = roommateAd.getTitle();
        info = roommateAd.getInfo();
        author = new UserSummary(roommateAd.getUser());
        region = roommateAd.getRegion().getName();
        regionId = roommateAd.getRegion().getId().toString();
        date = DateUtil.getHowLongAgoString(roommateAd.getDate());
        amount = roommateAd.getAmount();
        roomSize = roommateAd.getSize();
        contact = roommateAd.getPhone();
    }

}
