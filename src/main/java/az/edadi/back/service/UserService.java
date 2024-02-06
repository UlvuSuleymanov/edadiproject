package az.edadi.back.service;

import az.edadi.back.entity.auth.User;
import az.edadi.back.model.UserPrincipalModel;
import az.edadi.back.model.request.SetSpecialityRequestModel;
import az.edadi.back.model.response.SignInResponseModel;
import az.edadi.back.model.response.UserResponseModel;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public interface UserService {
    UserResponseModel getUserByUsername(String username);
    UserPrincipalModel createUserPrincipial(User user);
    String setImage(MultipartFile multipartFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    void setSpeciality(SetSpecialityRequestModel setSpecialityRequestModel);
    SignInResponseModel getCurrentUser();

}
