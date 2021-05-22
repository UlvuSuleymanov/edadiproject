package az.edadi.back.service;
import az.edadi.back.entity.User;
import az.edadi.back.model.response.ImageResponseModel;
import az.edadi.back.model.UserPrincipalModel;
import az.edadi.back.model.response.UserResponseModel;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {


     UserResponseModel getUserByUsername(String username);



      UserPrincipalModel createUserPrincipial(User user);

      ImageResponseModel setImage(MultipartFile multipartFile);

 //     String setPhoto(MultipartFile multipartFile ,String username);

//      SignInResponseModel login (SignInRequestModel signInRequestModel);
//      User userBuilder(SignUpRequestModel signUpRequestModel);
//
//      boolean checkPassword(String password);


}
