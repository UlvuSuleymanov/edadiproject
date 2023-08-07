package az.edadi.back.model.request;

import az.edadi.back.validation.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class NewPasswordRequestModel {

    @NotBlank
    private String token;

    @Password
    private  String password;
}
