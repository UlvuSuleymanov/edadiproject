package az.edadi.back.service;
import az.edadi.back.entity.User;
import az.edadi.back.model.ImageResponseModel;
import az.edadi.back.model.UserPrincipalModel;
import az.edadi.back.model.response.UserResponseModel;
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

      ImageResponseModel setImage(MultipartFile multipartFile);

 //     String setPhoto(MultipartFile multipartFile ,String username);

//      SignInResponseModel login (SignInRequestModel signInRequestModel);
//      User userBuilder(SignUpRequestModel signUpRequestModel);
//
//      boolean checkPassword(String password);


}
