package az.edadi.back.model;

import az.edadi.back.constants.PhotoEnum;
import az.edadi.back.entity.User;
import az.edadi.back.utility.PhotoUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaAuthor {
    private String name;
    private String username;
    private String photoUrl;

    public MediaAuthor(User user){
        name=user.getName();
        username=user.getUsername();
        photoUrl= PhotoUtil.getThumbPhotoUrl(user.getPhotoUrl());
    }
}
