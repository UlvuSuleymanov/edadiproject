package az.edadi.back.service.impl.auth;

import az.edadi.back.constants.AppConstants;
import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.auth.Login;
import az.edadi.back.entity.auth.User;
import az.edadi.back.exception.model.EdadiEntityNotFoundException;
import az.edadi.back.exception.model.TooManyAttemptException;
import az.edadi.back.exception.model.UsernameOrPasswordNotCorrectException;
import az.edadi.back.model.request.OAuth2LoginRequest;
import az.edadi.back.model.request.SignInRequestModel;
import az.edadi.back.model.request.SignUpRequestModel;
import az.edadi.back.model.response.JwtTokenResponseModel;
import az.edadi.back.model.response.OAuth2CustomUser;
import az.edadi.back.model.response.SignInResponseModel;
import az.edadi.back.repository.LoginRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.security.listener.event.LoginEvent;
import az.edadi.back.service.AuthenticationService;
import az.edadi.back.service.JwtService;
import az.edadi.back.service.LoginAttemptService;
import az.edadi.back.service.MailService;
import freemarker.template.TemplateException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MailService mailService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final LoginAttemptService loginAttemptService;
    private final LoginRepository loginRepository;

    @Override
    public void register(SignUpRequestModel signUpRequestModel) throws UsernameNotFoundException {
        User user = new User(signUpRequestModel);
        user.setPassword(passwordEncoder.encode(signUpRequestModel.getPassword()));
        userRepository.save(user);

    }

    @Override
    public String sendTokenByEmail(String usernamOrEmail) throws MessagingException, IOException, TemplateException, jakarta.mail.MessagingException {
       User user = userRepository.findByUsernameOrEmail(usernamOrEmail, usernamOrEmail)
               .orElseThrow(()-> new EdadiEntityNotFoundException(EntityType.USER));

        String token = jwtService.generateResetPasswordToken(user);
        String link = AppConstants.DOMAIN + "/recovery?token=" + token;
        Map<String, String> mailModel = new HashMap<>();
        mailModel.put("name", user.getName());
        mailModel.put("link", link);
        mailService.sendResetPasswordMail(user.getEmail(), mailModel);

        return getSecureEmail(user.getEmail());


    }

    @Override
    public SignInResponseModel resetPassword(String token, String newPassword) {
        Long untrustedId = jwtService.getUntrustedIdFromToken(token);
        Optional<User> user = userRepository.findById(untrustedId);

        if (!user.isPresent())
            throw new EdadiEntityNotFoundException(EntityType.USER);
        User u = user.get();
        Long id = jwtService.getIdFromToken(token, user.get());
        u.setPassword(passwordEncoder.encode(newPassword));
        u = userRepository.save(u);

        return new SignInResponseModel(u, jwtService.getTokenResponse(u));
    }

    @Override
    public JwtTokenResponseModel refreshToken(JwtTokenResponseModel tokens) {
        Long accessId = jwtService.getUntrustedIdFromToken(tokens.getRefreshToken());

        Optional<User> user = userRepository.findById(accessId);

        if (!user.isPresent())
            throw new EdadiEntityNotFoundException(EntityType.USER);

        //check refreshToken
        jwtService.getIdFromToken(tokens.getRefreshToken(), user.get());

        return jwtService.getTokenResponse(user.get());

    }

    @Override
    public SignInResponseModel socialLogin(OAuth2LoginRequest oAuth2LoginRequest) {
        OAuth2CustomUser oAuth2CustomUser = verifyToken(oAuth2LoginRequest.getToken());
        Optional<User> user = userRepository.findByEmail(oAuth2CustomUser.getEmail());
        if (user.isPresent())
            return new SignInResponseModel(user.get(), jwtService.getTokenResponse(user.get()));

        User newUser = new User(oAuth2CustomUser);
        newUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString().substring(4, 20)));
        User saved = userRepository.saveAndFlush(newUser);
        return new SignInResponseModel(saved, jwtService.getTokenResponse(saved));
    }

    OAuth2CustomUser verifyToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://oauth2.googleapis.com/tokeninfo?id_token=" + token;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<OAuth2CustomUser> result = restTemplate.exchange(uri, HttpMethod.GET, entity, OAuth2CustomUser.class);
        return result.getBody();
    }



    String getSecureEmail(String email) {
        char[] c = email.toCharArray();
        int length = c.length;

        for (int i = 1; i < length - 1; i++) {
            if (c[i + 1] == '@' || c[i] == '@')
                continue;
            c[i] = '*';
        }
        c[length - 1] = '*';
        return String.valueOf(c);
    }


    @Override
    public SignInResponseModel login(SignInRequestModel signInRequestModel) {

        if (!loginAttemptService.isGoodAttemmpt())
            throw new TooManyAttemptException();

        User user = userRepository.findByUsernameOrEmail(signInRequestModel.getUsername(), signInRequestModel.getUsername())
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with username or email : " + signInRequestModel.getUsername())
                );

        if (!passwordEncoder.matches(signInRequestModel.getPassword(), user.getPassword())) {
            applicationEventPublisher.publishEvent(new LoginEvent(false));
            throw new UsernameOrPasswordNotCorrectException();
        }

        loginRepository.save(new Login(user));

        applicationEventPublisher.publishEvent(new LoginEvent(true));
        return new SignInResponseModel(user, jwtService.getTokenResponse(user));


    }


}
