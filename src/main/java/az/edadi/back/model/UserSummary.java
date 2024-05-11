package az.edadi.back.model;

import az.edadi.back.entity.auth.User;
import az.edadi.back.utility.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummary {
    private Long id;
    private String name;
    private String username;
    private String picture;
    private String lang="az";
    private String lastSeen;

    public UserSummary(User user){
        id=user.getId();
        name=user.getName();
        username=user.getUsername();
        picture=user.getPicture();
        if(user.isShownLastSeen())
          lastSeen= DateUtil.getHowLongAgoString(user.getLastSeen());
    }
}
