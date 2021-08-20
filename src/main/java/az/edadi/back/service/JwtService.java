package az.edadi.back.service;

import az.edadi.back.entity.User;
import az.edadi.back.entity.UserAuthority;
import az.edadi.back.model.JwtTokenResponseModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Set;

public interface JwtService {

    JwtTokenResponseModel getTokenResponse(User user);
    String generateAccessToken(User user);
    String generateRefreshToken(User user);
    String generateResetPasswordToken(User user);
    public Claims getAccessTokenClaims(String token);
    Long getUntrustedIdFromToken(String token);
    Long getIdFromToken(String token, User user);

}
