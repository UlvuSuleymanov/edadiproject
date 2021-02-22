package com.camaat.first.service.impl;

import com.camaat.first.entity.User;
import com.camaat.first.entity.UserAuthority;
import com.camaat.first.model.request.SignInRequestModel;
import com.camaat.first.model.request.SignUpRequestModel;
import com.camaat.first.model.response.SignInResponseModel;
import com.camaat.first.model.response.SignUpResponseModel;
import com.camaat.first.repository.UserRepository;
import com.camaat.first.security.jwt.JwtBean;
import com.camaat.first.security.jwt.JwtProvider;
import com.camaat.first.service.AuthenticationService;
import com.camaat.first.service.UserService;
import com.camaat.first.utility.ImageUtil;
import com.camaat.first.utility.UserEnum;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private  final UserRepository userRepository;
    private  final UserService userService;
    private  final PasswordEncoder passwordEncoder;
    private final JwtBean jwtBean;

    public AuthenticationServiceImpl(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder, JwtBean jwtBean) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtBean = jwtBean;
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

        boolean isGood=true;

        if(password!=null) {

            if (password.trim().length() < 6)
                isGood= false;
            else {
                isGood=true;
            }


        }
        else{
            isGood=false;
        }



        return isGood;
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
       User newUser= userBuilder(signUpRequestModel);
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
        JwtProvider jwtProvider = new JwtProvider(jwtBean);
        String username = signInRequestModel.getUsername();
        String password = signInRequestModel.getPassword();
        User user;


        if(userRepository.existsByUsername(username) || userRepository.existsByEmail(username))
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

        String token = jwtProvider.jwtBuilder(user.getUsername(),user.getId(), user.getAuthorities());

        return  new SignInResponseModel(user.getUsername(),token,true,true, ImageUtil.generatePhotoUrl(user.getPhotoUrl()) );



    }



    @Override
    public User     userBuilder(SignUpRequestModel signUpRequestModel) {

        if(signUpRequestModel.isStudent()){

        }
        User user = new User();
        user.setName(signUpRequestModel.getName());
        user.setEmail(signUpRequestModel.getEmail());
        user.setUsername(signUpRequestModel.getUsername());
        user.setPhotoUrl(UserEnum.DEFAULT_USER_IMAGE_NAME.getImageName());
        user.getAuthorities().add(UserAuthority.USER_READ);

        user.setPassword(passwordEncoder.encode(signUpRequestModel.getPassword()));
        return user;

    }



}
