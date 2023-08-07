package az.edadi.back.model.request;

import az.edadi.back.validation.NotDublicateEmail;
import az.edadi.back.validation.NotDublicateUsername;
import az.edadi.back.validation.Password;
import az.edadi.back.validation.Username;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestModel {

    @NotDublicateUsername(message = "Username is exists")
    @Username(message = "invalid username string")
    private String username;

    @Password
    private String password;


    @Size(min = 1,max = 30)
    private String name;

    @Email
    @NotDublicateEmail
    private String email;


}
