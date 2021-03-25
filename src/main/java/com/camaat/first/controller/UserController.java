package com.camaat.first.controller;
  import com.camaat.first.model.ImageResponseModel;
  import com.camaat.first.model.response.UserResponseModel;
 import com.camaat.first.repository.UserRepository;
 import com.camaat.first.service.UserService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
  import org.springframework.security.access.prepost.PreAuthorize;
  import org.springframework.web.bind.annotation.*;
  import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("api/user")
  public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

     @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
         this.userRepository = userRepository;
         this.userService = userService;
     }


     @GetMapping("/{username}")
     ResponseEntity getUserDetails(@PathVariable String username){
         UserResponseModel userResponseModel=null;

         boolean isExists = userRepository.existsByUsername(username);

         if(isExists)
            userResponseModel = userService.createUserSerponseModel(username);


     return  ResponseEntity.ok(userResponseModel);
     }


    @PostMapping("/api/user/{username}/image")
    @PreAuthorize(value = "#username == authentication.name")
    public ResponseEntity setImage(@RequestParam("image") MultipartFile multipartFile,
                                   @PathVariable String username){

        ImageResponseModel responseModel= userService.setImage(multipartFile);

        return ResponseEntity.ok(responseModel);
    }





}
