package az.edadi.back.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtBean {

    private String title;
    private String secretKey;
    private Long accessTokenSessionTime;
    private Long refreshTokenSessionTime;


}
