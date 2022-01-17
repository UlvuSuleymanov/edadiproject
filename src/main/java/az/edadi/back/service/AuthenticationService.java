package az.edadi.back.service;

import az.edadi.back.model.request.SignInRequestModel;
import az.edadi.back.model.request.SignUpRequestModel;
import az.edadi.back.model.response.JwtTokenResponseModel;
import az.edadi.back.model.response.SignInResponseModel;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface AuthenticationService {

    SignInResponseModel login(SignInRequestModel signInRequestModel);

    void register(SignUpRequestModel signUpRequestModel);

    String sendTokenByEmail(String usernamOrEmail) throws MessagingException, IOException, TemplateException;

    SignInResponseModel resetPassword(String token, String newPassword);

    JwtTokenResponseModel refreshToken(JwtTokenResponseModel tokens);

}
