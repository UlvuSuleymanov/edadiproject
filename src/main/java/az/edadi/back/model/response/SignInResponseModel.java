package az.edadi.back.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseModel {
    private String username;
    private String token;
    private String photoUrl;



    }
