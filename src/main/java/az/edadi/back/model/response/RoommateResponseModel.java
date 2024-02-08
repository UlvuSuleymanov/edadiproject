package az.edadi.back.model.response;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.entity.roommate.Roommate;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.AuthUtil;
import az.edadi.back.utility.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class RoommateResponseModel {
    private Long id;
    private String info;
    private UserSummary author;
    private String region;
    private String regionId;
    private String date;
    private String contact;
    private boolean canDelete;


    public RoommateResponseModel(Roommate roommate) {
        id = roommate.getId();
        info = roommate.getGeneralInfo();
        author = new UserSummary(roommate.getUser());
        region = roommate.getRegion().getName();
        regionId = roommate.getRegion().getId().toString();
        date = DateUtil.getHowLongAgoString(roommate.getDate());
         contact = roommate.getContact();
        canDelete = AuthUtil.userIsAuthenticated() && (
                roommate.getUser().getId().equals(AuthUtil.getCurrentUserId()) || AuthUtil.hasAuthority(UserAuthority.ADMIN_UPDATE)
        );
    }

}
