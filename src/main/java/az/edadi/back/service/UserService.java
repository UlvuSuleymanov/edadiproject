package az.edadi.back.service;

import az.edadi.back.entity.auth.User;
import az.edadi.back.model.UserPrincipalModel;
import az.edadi.back.model.request.SetSpecialityRequestModel;
import az.edadi.back.model.response.UserResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UserService {
    UserResponseModel getUserByUsername(String username);
    UserPrincipalModel createUserPrincipial(User user);
    String setImage(MultipartFile multipartFile) throws IOException;
    void setSpeciality(SetSpecialityRequestModel setSpecialityRequestModel);

}
