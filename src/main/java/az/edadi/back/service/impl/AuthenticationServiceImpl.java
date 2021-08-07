package az.edadi.back.service.impl;

import az.edadi.back.entity.User;
import az.edadi.back.exception.UserNotFoundException;
import az.edadi.back.exception.UsernameOrPasswordNotCorrectException;
import az.edadi.back.repository.SpecialityOfferRepository;
import az.edadi.back.repository.SpecialityRepository;
import az.edadi.back.repository.UniversityRepository;
import az.edadi.back.repository.UserRepository;
 import az.edadi.back.security.jwt.JwtProvider;
import az.edadi.back.service.MailService;
import az.edadi.back.service.SpecialityService;
import az.edadi.back.service.UserService;

 import az.edadi.back.model.request.SignInRequestModel;
import az.edadi.back.model.request.SignUpRequestModel;
import az.edadi.back.model.response.SignInResponseModel;
 import az.edadi.back.service.AuthenticationService;

import az.edadi.back.utility.PhotoUtil;
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
    private final JwtProvider jwtProvider;
    private final SpecialityRepository specialityRepository;
    private final UniversityRepository universityRepository;
    private final SpecialityService specialityService;
    private final SpecialityOfferRepository specialityOfferRepository;
    private final MailService mailService;

     @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     UserService userService,
                                     PasswordEncoder passwordEncoder,
                                     JwtProvider jwtProvider,
                                     SpecialityRepository specialityRepository,
                                     UniversityRepository universityRepository,
                                     SpecialityService specialityService,
                                     SpecialityOfferRepository specialityOfferRepository,
                                     MailService mailService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
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
        Optional<User> user = userRepository.findByUsernameOrEmail(usernamOrEmail,usernamOrEmail);
        if(!user.isPresent())
            throw new UserNotFoundException();

        String token = jwtProvider.jwtBuilder(user.get().getId());
        String link = "http://edadi.az/recover?token="+token;
        Map<String,String> mailModel = new HashMap<>();
        mailModel.put("name",user.get().getName());
        mailModel.put("link",link);
        mailService.sendResetPasswordMail(user.get().getEmail(), mailModel);

        return getSecureEmail(user.get().getEmail());


    }

    String getSecureEmail(String email){
         char c[]=email.toCharArray();
         int length=c.length;

         for(int i=1;i<length-1;i++){
            if(c[i]=='.' || c[i+1]=='@' ||c[i]=='@'  )
                continue;
            c[i]='*';
         }
         c[length-1]='*';
         return String.valueOf(c);
    }


    @Override
    public SignInResponseModel login(SignInRequestModel signInRequestModel) {
         User  user = userRepository.findByUsernameOrEmail(signInRequestModel.getUsername(),signInRequestModel.getUsername())
                 .orElseThrow(() ->
                         new EntityNotFoundException("User not found with username or email : " + signInRequestModel.getUsername())
                 );;

         if(!passwordEncoder.matches(signInRequestModel.getPassword(),user.getPassword()) )
        {
            throw new UsernameOrPasswordNotCorrectException();
        }

         return new SignInResponseModel(
                 user.getUsername(),
                 jwtProvider.jwtBuilder(user.getUsername(),user.getId(), user.getAuthorities()),
                 PhotoUtil.getThumbPhotoUrl(user.getPhotoUrl())

         );



    }



}
