package com.camaat.first.security.jwt;

import com.camaat.first.entity.UserAuthority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Set;

public class JwtProvider {
    private  final JwtBean jwtBean;

    public JwtProvider(JwtBean jwtBean) {
        this.jwtBean = jwtBean;
    }


    public  String jwtBuilder(String username,
                                    Long id,
                                    Set<UserAuthority> authorities
                                    ) {
         Date birthDay  = new Date();
         Date deathDate = new Date(System.currentTimeMillis() + jwtBean.getLifeTime());

         return Jwts.builder()
                 .setSubject(username)
                 .claim("authorities",authorities)
                 .claim("id",id)
                 .setExpiration(deathDate)
                 .setIssuedAt(birthDay)
                 .signWith(SignatureAlgorithm.HS512, jwtBean.getSecretKey())
                 .compact();


    }


    public   String getUsernameFromToken(String token) {
         return Jwts.parser()
                .setSigningKey(jwtBean.getSecretKey())
                .parseClaimsJws(token)
                .getBody().getSubject();

    }
     public  Claims getTokenClaims(String token) {
         Claims claims = Jwts.parser()
                 .setSigningKey(jwtBean.getSecretKey())
                 .parseClaimsJws(token)
                 .getBody();
            return claims;

     }


         public static boolean checkToken(String token, JwtBean jwtBean) {
        try {

            Jwts.parser().setSigningKey(jwtBean.getSecretKey()).parseClaimsJws(token);
            return true;
        } catch (JwtException je) {

            return false;

        }

    }
}
