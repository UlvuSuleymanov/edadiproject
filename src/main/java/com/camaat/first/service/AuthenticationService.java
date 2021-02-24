package com.camaat.first.service;

  import com.camaat.first.model.response.SignInResponseModel;
  import com.camaat.first.model.request.SignInRequestModel;
 import com.camaat.first.model.request.SignUpRequestModel;
 import com.camaat.first.model.response.SignUpResponseModel;

public interface AuthenticationService {
  SignUpResponseModel signUp(final SignUpRequestModel signUpRequestModel);

  SignInResponseModel signIn(SignInRequestModel signInRequestModel);








  boolean setUsername(String username);
  boolean setPassword(String password);
  boolean setEmail(String email);
  boolean setSpeciality(Long specialityId);
  boolean setName(String name);
  boolean setUniversity(Long uniId);




//    SignInResponseModel login (SignInRequestModel signInRequestModel);
//    User userBuilder(SignUpRequestModel signUpRequestModel);

 }
