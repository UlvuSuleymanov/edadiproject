package az.edadi.back.model;

import az.edadi.back.constants.PhotoEnum;
import az.edadi.back.entity.User;
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
    private ImageModel image;

    public UserSummary(User user){
        id=user.getId();
        name=user.getName();
        username=user.getUsername();
        image= new ImageModel(user.getImageName(), PhotoEnum.USER_IMAGE_FOLDER);
    }
}
