package az.edadi.back.model.response;

import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.university.Speciality;
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
    private String picture;
    private String date;
    private String university;
    private String speciality;
    private int postCount;
    private int commentCount;

    public UserResponseModel(User user) {
        username = user.getUsername();
        name = user.getName();
        date = DateUtil.getHowLongAgoString(user.getDateCreated());
        picture=user.getPicture();
        Optional<Speciality> speciality  = Optional.ofNullable(user.getSpeciality());
        if(speciality.isPresent()){
            this.speciality=speciality.get().getName();
            this.university=speciality.get().getUniversity().getNameAz();
        }

    }

}

