package az.edadi.back.model.response;

import az.edadi.back.entity.User;
import az.edadi.back.model.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel {

    private String username;
    private String name;
    private ImageModel image;
    private int postCount;
    private int commentCount;

    public UserResponseModel(User user) {
        username = user.getUsername();
        name = user.getName();
        image = new ImageModel(user.getImageName(),"user");
    }

}

