package com.camaat.first.service;

  import com.camaat.first.model.response.SignInResponseModel;
 import com.camaat.first.entity.User;
 import com.camaat.first.model.request.SignInRequestModel;
 import com.camaat.first.model.request.SignUpRequestModel;
 import com.camaat.first.model.response.SignUpResponseModel;

public interface AuthenticationService {
  SignUpResponseModel signUp(final SignUpRequestModel signUpRequestModel);
  SignUpResponseModel checkUserCredentials(SignUpRequestModel signUpRequestModel);
   boolean checkUsername(String username);
  boolean checkPassword(String password);
  boolean checkEmail(String email);
  boolean checkName(String name);
  User saveUser(SignUpRequestModel signUpRequestModel);
  SignInResponseModel signIn(SignInRequestModel signInRequestModel);


//    SignInResponseModel login (SignInRequestModel signInRequestModel);
    User userBuilder(SignUpRequestModel signUpRequestModel);

 }
