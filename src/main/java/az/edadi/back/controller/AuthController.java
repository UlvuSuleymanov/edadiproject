package az.edadi.back.controller;

import az.edadi.back.model.request.*;
import az.edadi.back.model.response.JwtTokenResponseModel;
import az.edadi.back.model.response.SignInResponseModel;
import az.edadi.back.service.AuthenticationService;

import freemarker.template.TemplateException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import java.io.IOException;

@RestController
@RequestMapping(value = "api/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("refresh")
    public ResponseEntity refreshToken(@RequestBody JwtTokenResponseModel tokens) {
        return ResponseEntity.ok(authenticationService.refreshToken(tokens));
    }

    @PostMapping(value = "signin")
    public ResponseEntity<?> login(@RequestBody SignInRequestModel signInRequestModel) {
        return ResponseEntity.ok(authenticationService.login(signInRequestModel));
    }

    @PostMapping(value = "signup")
    public ResponseEntity addUser(@Valid @RequestBody final SignUpRequestModel signUpRequestModel) {
        authenticationService.register(signUpRequestModel);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(value = "google")
    public ResponseEntity addUser(@RequestBody OAuth2LoginRequest request) {
        SignInResponseModel signInResponseModel = authenticationService.socialLogin(request);
        return ResponseEntity.ok(signInResponseModel);
    }

    @PostMapping(value = "/recovery")
    public ResponseEntity sendToken(@RequestBody PasswordRecoverRequest emailOrUsername) throws MessagingException, IOException, TemplateException, jakarta.mail.MessagingException {
        return ResponseEntity.ok(new PasswordRecoverRequest(authenticationService.sendTokenByEmail(emailOrUsername.getUsernameOrEmail())));
    }

    @PutMapping(value = "/recovery")
    public ResponseEntity recoverPassword(@Valid @RequestBody NewPasswordRequestModel password) {
        return ResponseEntity.ok(authenticationService.resetPassword(password.getToken(), password.getPassword()));
    }


}
