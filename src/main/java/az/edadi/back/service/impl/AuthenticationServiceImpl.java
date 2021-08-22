package az.edadi.back.service.impl;

import az.edadi.back.entity.User;
import az.edadi.back.exception.custom.UserNotFoundException;
import az.edadi.back.exception.custom.UsernameOrPasswordNotCorrectException;
import az.edadi.back.model.JwtTokenResponseModel;
import az.edadi.back.repository.SpecialityOfferRepository;
import az.edadi.back.repository.SpecialityRepository;
import az.edadi.back.repository.UniversityRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.*;


import az.edadi.back.model.request.SignInRequestModel;
import az.edadi.back.model.request.SignUpRequestModel;
import az.edadi.back.model.response.SignInResponseModel;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final SpecialityRepository specialityRepository;
    private final UniversityRepository universityRepository;
    private final SpecialityService specialityService;
    private final SpecialityOfferRepository specialityOfferRepository;
    private final MailService mailService;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     UserService userService,
                                     PasswordEncoder passwordEncoder,
                                     JwtService jwtService,
                                     SpecialityRepository specialityRepository,
                                     UniversityRepository universityRepository,
                                     SpecialityService specialityService,
                                     SpecialityOfferRepository specialityOfferRepository,
                                     MailService mailService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.specialityRepository = specialityRepository;
        this.universityRepository = universityRepository;
        this.specialityService = specialityService;
        this.specialityOfferRepository = specialityOfferRepository;
        this.mailService = mailService;
    }


    @Override
    public void register(SignUpRequestModel signUpRequestModel) throws UsernameNotFoundException {
        User user = new User(signUpRequestModel);
        user.setPassword(passwordEncoder.encode(signUpRequestModel.getPassword()));


        userRepository.save(user);

    }

    @Override
    public String sendTokenByEmail(String usernamOrEmail) throws MessagingException, IOException, TemplateException {
        Optional<User> user = userRepository.findByUsernameOrEmail(usernamOrEmail, usernamOrEmail);
        if (!user.isPresent())
            throw new UserNotFoundException();

        String token = jwtService.generateResetPasswordToken(user.get());
        String link = "http://edadi.az/recovery?token=" + token;
        Map<String, String> mailModel = new HashMap<>();
        mailModel.put("name", user.get().getName());
        mailModel.put("link", link);
        mailService.sendResetPasswordMail(user.get().getEmail(), mailModel);

        return getSecureEmail(user.get().getEmail());


    }

    @Override
    public SignInResponseModel resetPassword(String token, String newPassword) {
        Long untrustedId = jwtService.getUntrustedIdFromToken(token);
        Optional<User> user = userRepository.findById(untrustedId);

        if (!user.isPresent())
            throw new UserNotFoundException();
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
            throw new UserNotFoundException();

        //check refreshToken
        jwtService.getIdFromToken(tokens.getRefreshToken(),user.get());

        return jwtService.getTokenResponse(user.get());

    }

    String getSecureEmail(String email) {
        char[] c = email.toCharArray();
        int length = c.length;

        for (int i = 1; i < length - 1; i++) {
            if (c[i] == '.' || c[i + 1] == '@' || c[i] == '@')
                continue;
            c[i] = '*';
        }
        c[length - 1] = '*';
        return String.valueOf(c);
    }


    @Override
    public SignInResponseModel login(SignInRequestModel signInRequestModel) {

        User user = userRepository.findByUsernameOrEmail(signInRequestModel.getUsername(), signInRequestModel.getUsername())
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with username or email : " + signInRequestModel.getUsername())
                );

        if (!passwordEncoder.matches(signInRequestModel.getPassword(), user.getPassword())) {
            throw new UsernameOrPasswordNotCorrectException();
        }

        return new SignInResponseModel(user, jwtService.getTokenResponse(user));


    }

}
