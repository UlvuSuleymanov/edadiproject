package az.edadi.back.model.res;

import az.edadi.back.entity.roommate.Roommate;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.DateUtil;

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


}
