package com.camaat.first.controller;
  import com.camaat.first.model.response.UserResponseModel;
 import com.camaat.first.repository.UserRepository;
 import com.camaat.first.service.UserService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.*;


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



//    @PreAuthorize(value = "#username == authentication.name")
//    @PostMapping (value = "/{username}/image")
//         public ResponseEntity setImage(@RequestParam("image") MultipartFile multipartFile,
//                                        @PathVariable String username){
//
//           String url = userService.setPhoto(multipartFile,username);
//           return ResponseEntity.ok(new ImageModel(url));
//     }




}
