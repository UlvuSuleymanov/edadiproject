package az.edadi.back.service;

import az.edadi.back.entity.User;
import az.edadi.back.model.ImageModel;
import az.edadi.back.model.UserPrincipalModel;
import az.edadi.back.model.response.UserResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UserService {


    UserResponseModel getUserByUsername(String username);


    UserPrincipalModel createUserPrincipial(User user);

    ImageModel setImage(MultipartFile multipartFile) throws IOException;


    //     String setPhoto(MultipartFile multipartFile ,String username);

//      SignInResponseModel login (SignInRequestModel signInRequestModel);
//      User userBuilder(SignUpRequestModel signUpRequestModel);
//
//      boolean checkPassword(String password);


}
