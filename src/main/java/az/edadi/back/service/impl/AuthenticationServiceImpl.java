package az.edadi.back.service.impl;

import az.edadi.back.entity.User;
import az.edadi.back.exception.UsernameOrPasswordNotCorrectException;
import az.edadi.back.repository.SpecialityOfferRepository;
import az.edadi.back.repository.SpecialityRepository;
import az.edadi.back.repository.UniversityRepository;
import az.edadi.back.repository.UserRepository;
 import az.edadi.back.security.jwt.JwtProvider;
import az.edadi.back.service.SpecialityService;
import az.edadi.back.service.UserService;

 import az.edadi.back.model.request.SignInRequestModel;
import az.edadi.back.model.request.SignUpRequestModel;
import az.edadi.back.model.response.SignInResponseModel;
 import az.edadi.back.service.AuthenticationService;

import az.edadi.back.utility.PhotoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


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

     @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     UserService userService,
                                     PasswordEncoder passwordEncoder,
                                     JwtProvider jwtProvider,
                                     SpecialityRepository specialityRepository,
                                     UniversityRepository universityRepository,
                                     SpecialityService specialityService,
                                     SpecialityOfferRepository specialityOfferRepository
                                   ) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.specialityRepository = specialityRepository;
        this.universityRepository = universityRepository;
        this.specialityService = specialityService;
        this.specialityOfferRepository = specialityOfferRepository;
     }


    @Override
    public void register(SignUpRequestModel signUpRequestModel) throws UsernameNotFoundException {
        User user = new User(signUpRequestModel);
        user.setPassword(passwordEncoder.encode(signUpRequestModel.getPassword()));


         userRepository.save(user);

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
                 PhotoUtil.getFullPhotoUrl(user.getPhotoUrl())


         );



    }



}
