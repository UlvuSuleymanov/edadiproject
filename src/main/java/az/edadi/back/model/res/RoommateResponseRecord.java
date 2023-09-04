package az.edadi.back.model.res;

import az.edadi.back.entity.roommate.RoommateAd;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.DateUtil;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;

public record RoommateResponseRecord(
        Long id,
        String info,
        UserSummary author,
        String region,
        String regionId,
        String date,
        Integer amount,
        String contact,
        List<String> urls
) {
    public RoommateResponseRecord(RoommateAd roommateAd) {
        this(roommateAd.getId(),
                roommateAd.getInfo(),
        new UserSummary(roommateAd.getUser()),
        roommateAd.getRegion().getName(),
        roommateAd.getRegion().getId().toString(),
        DateUtil.getHowLongAgoString(roommateAd.getDate()),
        roommateAd.getAmount(),
        roommateAd.getContact(),
                Arrays.asList("")
        );
    }

}
