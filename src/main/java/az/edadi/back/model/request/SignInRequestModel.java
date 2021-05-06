package az.edadi.back.model.request;

import az.edadi.back.validation.Username;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestModel {
    @Username
    private String username;

    @Size(min = 6,max = 20)
    private  String password;
}
