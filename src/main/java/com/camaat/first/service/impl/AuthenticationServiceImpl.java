package com.camaat.first.service.impl;

import com.camaat.first.entity.User;
import com.camaat.first.model.request.SignInRequestModel;
import com.camaat.first.model.request.SignUpRequestModel;
import com.camaat.first.model.response.SignInResponseModel;
import com.camaat.first.model.response.SignUpResponseModel;
import com.camaat.first.repository.UserRepository;
import com.camaat.first.service.AuthenticationService;
import com.camaat.first.service.UserService;
import com.camaat.first.utility.DataParser;
import com.camaat.first.utility.ImageUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private  final UserRepository userRepository;
    private  final UserService userService;
    private  final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpResponseModel signUp(final SignUpRequestModel signUpRequestModel) {
        SignUpResponseModel signUpResponseModel =  checkUserCredentials(signUpRequestModel);
        if(signUpResponseModel.isNewUserIsValid())
        {
            User user = saveUser(signUpRequestModel);
        }

        return signUpResponseModel;

    }

    @Override
    public boolean checkUsername(String username) {
        boolean isGoodUsername=true;
        if(username.length()>40)
        {
            isGoodUsername=false;
            return isGoodUsername;
        }

        isGoodUsername = !userRepository.existsByUsername(username);

        return isGoodUsername;
    }

    @Override
    public boolean checkPassword(String password) {
        boolean isGoodPassword=true;
        if(password.length()<6)
        {
            isGoodPassword= false;
        }

        return isGoodPassword;
    }

    @Override
    public boolean checkEmail(String email) {
      boolean isGoodEmail=true;
      isGoodEmail=!userRepository.existsByEmail(email);
      return isGoodEmail;

    }

    @Override
    public boolean checkName(String name) {
        return true;
    }

    @Override
    public User saveUser(SignUpRequestModel signUpRequestModel) {
       User newUser= userService.userBuilder(signUpRequestModel);
       User user = userRepository.save(newUser);
       return user;
    }



    @Override
    public SignUpResponseModel checkUserCredentials(SignUpRequestModel signUpRequestModel) {
        SignUpResponseModel signUpResponseModel = new SignUpResponseModel();
        signUpResponseModel
                .setEmailIsValid(checkEmail(signUpRequestModel.getEmail()))
                .setUsernameIsValid(checkUsername(signUpRequestModel.getUsername()))
                .setPasswordIsValid(checkPassword(signUpRequestModel.getPassword()));
        if(signUpResponseModel.isEmailIsValid()&&signUpResponseModel.isPasswordIsValid()&&signUpResponseModel.isUsernameIsValid() )
        {
            signUpResponseModel.setNewUserIsValid(true);
        }

        return signUpResponseModel;
    }


    @Override
    public SignInResponseModel signIn(SignInRequestModel signInRequestModel) {
        String username = signInRequestModel.getUsername();
        String password = signInRequestModel.getPassword();
        SignInResponseModel signInResponseModel=new SignInResponseModel();

        User  user ;
        boolean userIsExists =userRepository.existsByUsername(username) || userRepository.existsByEmail(username);


        if(userIsExists)
        {
            user = userRepository.findByUsernameOrEmail(username,username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found with username or email : " + username)
                    );

        }
        else {
            return  new SignInResponseModel("","",false,false,"");


        }


        if(!passwordEncoder.matches(password,user.getPassword())){
            return  new SignInResponseModel("","",true,false,"");

        }

//        String token = JwtProvider.jwtBuilder(user.getUsername(),user.getId(), user.getAuthorities(), jwtBean);
        String token =" JwtProvider.jwtBuilder(user.getUsername(),user.getId(), user.getAuthorities(), jwtBean);";

        return  new SignInResponseModel(user.getUsername(),token,true,true, ImageUtil.generatePhotoUrl(user.getPhotoUrl()) );



    }



}
