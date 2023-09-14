package az.edadi.back.model.response;

import lombok.Data;

@Data
public class OAuth2CustomUser {
    private String email;
    private String name;
    private String picture;
}
