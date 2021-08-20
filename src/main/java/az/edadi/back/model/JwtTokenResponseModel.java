package az.edadi.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenResponseModel {
   private String refreshToken;
   private String accessToken;
   private Long lifeTime;

}
