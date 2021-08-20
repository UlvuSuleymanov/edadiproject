package az.edadi.back.model.request;

import az.edadi.back.validation.Password;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class NewPasswordRequestModel {

    @NotBlank
    private String token;

    @Password
    private  String password;
}
