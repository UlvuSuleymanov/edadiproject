package az.edadi.back.model.response;

import az.edadi.back.entity.User;
import az.edadi.back.model.JwtTokenResponseModel;
import az.edadi.back.model.MediaAuthor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInResponseModel {

    private JwtTokenResponseModel token;
    private MediaAuthor author;

    public  SignInResponseModel(User user, JwtTokenResponseModel jwtTokenResponseModel){
        token=jwtTokenResponseModel;
        author=new MediaAuthor(user);
    }

}
