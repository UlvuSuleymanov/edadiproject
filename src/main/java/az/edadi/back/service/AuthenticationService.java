package az.edadi.back.service;

  import az.edadi.back.model.response.SignInResponseModel;
  import az.edadi.back.model.request.SignInRequestModel;
 import az.edadi.back.model.request.SignUpRequestModel;

public interface AuthenticationService {

  SignInResponseModel login(SignInRequestModel signInRequestModel);

   void register(SignUpRequestModel signUpRequestModel);

 }
