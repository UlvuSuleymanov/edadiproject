package az.edadi.back.service;

import az.edadi.back.entity.auth.User;
import az.edadi.back.model.response.JwtTokenResponseModel;
import io.jsonwebtoken.Claims;

public interface JwtService {

    JwtTokenResponseModel getTokenResponse(User user);
    String generateAccessToken(User user);
    String generateRefreshToken(User user);
    String generateResetPasswordToken(User user);
    Claims getAccessTokenClaims(String token);
    Long getUntrustedIdFromToken(String token);
    Long getIdFromToken(String token, User user);

}
