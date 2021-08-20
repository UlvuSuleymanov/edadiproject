package az.edadi.back.model.request;

import az.edadi.back.validation.NotDublicateEmail;
import az.edadi.back.validation.NotDublicateUsername;
import az.edadi.back.validation.Password;
import az.edadi.back.validation.Username;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestModel {

    @NotDublicateUsername(message = "Username is exists")
    @Username(message = "invalid username string")
    private String username;

    @Password
    private String password;


    @NotBlank
    private String name;

    @Email
    @NotDublicateEmail
    private String email;


}
