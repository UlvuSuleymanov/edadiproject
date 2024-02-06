package az.edadi.back.controller;

import az.edadi.back.model.ImageModel;
import az.edadi.back.model.request.SetSpecialityRequestModel;
import az.edadi.back.model.response.SignInResponseModel;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.UserService;
import az.edadi.back.utility.AuthUtil;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("api/user/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

     private final UserService userService;

    @GetMapping("/me")
    ResponseEntity getCurrentUser(){
        SignInResponseModel signInResponseModel = userService.getCurrentUser();
        return ResponseEntity.ok(signInResponseModel);
    }

    @GetMapping("{username}")
    ResponseEntity getUserDetails(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }


    @PostMapping("{username}/image")
    public ResponseEntity setImage(@RequestParam("image") MultipartFile multipartFile,
                                   @PathVariable String username) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String url = userService.setImage(multipartFile);
        return ResponseEntity.ok(new ImageModel(url));
    }
    @PostMapping("/speciality")
    public ResponseEntity setImage(@RequestBody SetSpecialityRequestModel speciality) {
       userService.setSpeciality(speciality);
       return ResponseEntity.ok(HttpStatus.OK);
     }


}
