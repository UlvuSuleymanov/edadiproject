package az.edadi.back.model.response;

import az.edadi.back.constants.AppConstants;
import az.edadi.back.entity.User;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.model.ImageModel;
import az.edadi.back.utility.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel {

    private String username;
    private String name;
    private ImageModel image;
    private String date;
    private String university;
    private String speciality;
    private int postCount;
    private int commentCount;

    public UserResponseModel(User user) {
        username = user.getUsername();
        name = user.getName();
        date = DateUtil.getHowLongAgoString(user.getProfileBirthDay());
        image = new ImageModel(user.getImageName(), AppConstants.USER_IMAGE_FOLDER);
        Optional<Speciality> speciality  = Optional.ofNullable(user.getSpeciality());
        if(speciality.isPresent()){
            this.speciality=speciality.get().getName();
            this.university=speciality.get().getUniversity().getNameAz();
        }

    }

}

