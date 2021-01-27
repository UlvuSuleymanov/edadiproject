package com.camaat.first.service;

import com.camaat.first.model.response.SignInResponseModel;
import com.camaat.first.model.response.UserResponseModel;
import com.camaat.first.entity.User;
import com.camaat.first.model.request.SignInRequestModel;
import com.camaat.first.model.request.SignUpRequestModel;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {

      /**
       * The Creator is God. But figuratively this method create a user.
       * First method check user details
       * if all values are suitable method save user :)
       * @param signUpRequestModel
       * @return
       */
      User userBuilder(SignUpRequestModel signUpRequestModel);


      UserResponseModel createUserSerponseModel(String username);




      String setPhoto(MultipartFile multipartFile ,String username);

      SignInResponseModel login (SignInRequestModel signInRequestModel);

      boolean checkPassword(String password);


}
