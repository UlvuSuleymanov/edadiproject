package az.edadi.back.model.request;

import az.edadi.back.validation.Password;
import az.edadi.back.validation.Username;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestModel {
    @Username
    private String username;

    @Password
    private  String password;
}
