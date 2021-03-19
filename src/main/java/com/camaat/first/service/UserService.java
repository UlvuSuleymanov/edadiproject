package com.camaat.first.service;
import com.camaat.first.entity.User;
import com.camaat.first.model.UserPrincipalModel;
import com.camaat.first.model.response.UserResponseModel;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {

      /**
       * The Creator is God. But figuratively this method create a user.
       * First method check user details
       * if all values are suitable method save user :)
       * @param
       * @return
       */


      UserResponseModel createUserSerponseModel(String username);


      UserPrincipalModel createUserPrincipial(User user);


 //     String setPhoto(MultipartFile multipartFile ,String username);

//      SignInResponseModel login (SignInRequestModel signInRequestModel);
//      User userBuilder(SignUpRequestModel signUpRequestModel);
//
//      boolean checkPassword(String password);


}
