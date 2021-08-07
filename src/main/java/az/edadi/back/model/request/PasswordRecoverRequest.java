package az.edadi.back.model.request;

import lombok.Data;

@Data
public class PasswordRecoverRequest {
   private String usernameOrEmail;
}
