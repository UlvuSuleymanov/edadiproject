package az.edadi.back.service.impl;

import az.edadi.back.entity.User;
import az.edadi.back.model.JwtTokenResponseModel;
import az.edadi.back.security.jwt.JwtBean;
import az.edadi.back.service.JwtService;
import az.edadi.back.utility.DataParser;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Date;
@Service
 public class JwtServiceImpl implements JwtService {

    private final int START_INDEX=5;
    private final int LAST_INDEX=15;

    private final JwtBean jwtBean;

    @Autowired
    public JwtServiceImpl(JwtBean jwtBean) {
        this.jwtBean = jwtBean;
    }

    @Override
    public Claims getAccessTokenClaims(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(jwtBean.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
        return claims;

    }

     @Override
     public Long getUntrustedIdFromToken(String token) {
         int i = token.lastIndexOf('.');
         String withoutSignature = token.substring(0, i+1);
         Jwt<Header,Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);
        return DataParser.objectToLong(untrusted.getBody().get("id"));
     }


     @Override
     public JwtTokenResponseModel getTokenResponse(User user) {
         String accessToken = generateAccessToken(user);
         String refreshToken = generateRefreshToken(user);
         return new JwtTokenResponseModel(refreshToken,accessToken,jwtBean.getAccessTokenSessionTime());
     }

     @Override
    public String generateAccessToken(User user) {
        Date birthDay  = new Date();
        Date deathDate = new Date(System.currentTimeMillis() + jwtBean.getAccessTokenSessionTime());

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities",user.getAuthorities())
                .claim("id",user.getId())
                .setExpiration(deathDate)
                .setIssuedAt(birthDay)
                .signWith(SignatureAlgorithm.HS512, jwtBean.getSecretKey())
                .compact();
    }

    @Override
    public String generateRefreshToken(User user) {
        Date birthDay  = new Date();
        Date deathDate = new Date(System.currentTimeMillis() + jwtBean.getRefreshTokenSessionTime());

        return Jwts.builder()
                .claim("id",user.getId())
                .setExpiration(deathDate)
                .setIssuedAt(birthDay)
                .signWith(SignatureAlgorithm.HS512, user.getPassword().substring(START_INDEX,LAST_INDEX))
                .compact();
    }

    @Override
    public String generateResetPasswordToken(User user) {
        Date birthDay  = new Date();
        Date deathDate = new Date(System.currentTimeMillis() + jwtBean.getAccessTokenSessionTime());

        return Jwts.builder()
                .claim("id",user.getId())
                .setExpiration(deathDate)
                .setIssuedAt(birthDay)
                .signWith(SignatureAlgorithm.HS512, user.getPassword().substring(START_INDEX,LAST_INDEX))
                .compact();
    }



     @Override
     public Long getIdFromToken(String token, User user) {
         Claims claims = Jwts.parser()
                 .setSigningKey(user.getPassword().substring(START_INDEX,LAST_INDEX))
                 .parseClaimsJws(token)
                 .getBody();
         Object id = claims.get("id");
         return DataParser.objectToLong(id);
     }

 }
