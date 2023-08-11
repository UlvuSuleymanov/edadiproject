package az.edadi.back.model.res;

import az.edadi.back.entity.roommate.RoommateAd;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.DateUtil;
import jakarta.validation.constraints.NotNull;

public record RoommateResponseRecord(
        Long id,
        String title,
        String info,
        UserSummary author,
        String region,
        String regionId,
        String date,
        Integer amount,
        Long roomSize,
        String contact
) {
    public RoommateResponseRecord(RoommateAd roommateAd) {
        this(roommateAd.getId(),
                roommateAd.getTitle(),
                roommateAd.getInfo(),
        new UserSummary(roommateAd.getUser()),
        roommateAd.getRegion().getName(),
        roommateAd.getRegion().getId().toString(),
        DateUtil.getHowLongAgoString(roommateAd.getDate()),
        roommateAd.getAmount(),
        roommateAd.getSize(),
        roommateAd.getPhone()
        );
    }

}
