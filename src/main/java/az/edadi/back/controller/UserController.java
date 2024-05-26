package az.edadi.back.controller;

import az.edadi.back.model.request.SetSpecialityRequestModel;
import az.edadi.back.model.request.UpdateUserImageReq;
import az.edadi.back.model.response.SignInResponseModel;
import az.edadi.back.model.response.UserResponseModel;
import az.edadi.back.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

     private final UserService userService;

    @GetMapping("/me")
    ResponseEntity<SignInResponseModel> getCurrentUser(){
        SignInResponseModel signInResponseModel = userService.getCurrentUser();
        return ResponseEntity.ok(signInResponseModel);
    }

    @GetMapping("{username}")
    ResponseEntity<UserResponseModel> getUserDetails(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping("me/image")
    public ResponseEntity<?> updateImage(@RequestBody UpdateUserImageReq updateUserImageReq) {
        String url = userService.updateUserImage(updateUserImageReq);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/speciality")
    public ResponseEntity<?> setImage(@RequestBody SetSpecialityRequestModel speciality) {
       userService.setSpeciality(speciality);
       return ResponseEntity.ok(HttpStatus.OK);
     }

}
