package az.edadi.back.model.response;

import az.edadi.back.entity.User;
import az.edadi.back.model.UserSummary;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInResponseModel {

    private JwtTokenResponseModel token;
    private UserSummary author;

    public SignInResponseModel(User user, JwtTokenResponseModel jwtTokenResponseModel) {
        token = jwtTokenResponseModel;
        author = new UserSummary(user);
    }

}
