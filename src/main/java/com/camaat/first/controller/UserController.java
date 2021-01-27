package com.camaat.first.controller;
 import com.camaat.first.model.ImageModel;
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


//    @GetMapping(value = "/check/username/{username}")
//    public IsValid usernamIsExsist(@PathVariable String username){
//        Boolean isValid =userRepository.existsByUsername(username);
//         return new IsValid(isValid);
//     }
//
//    @GetMapping(value = "/check/email/{email}")
//    public IsValid emailIsExsist(@PathVariable String email){
//        Boolean isValid =userRepository.existsByEmail(email);
//        return new IsValid(isValid);
//    }

//    @PreAuthorize(value = "#username == authentication.name")
//    @PutMapping (value = "/{username}/me")
//
//   public ResponseEntity<?> updateUserPrivateDetails(@PathVariable String username, @RequestBody UserDetails userDetails){
//
//            try {
//                userRepository.updateUser(userDetails.getName(), userDetails.getEmail(), userDetails.getUsername(),username);
//
//            }
//            catch (Exception e){
//
//                return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//
//         return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @PreAuthorize(value = "#username == authentication.name")
//    @GetMapping (value = "/{username}/me")
//      public UserDetails getUserPrivateDetails(@PathVariable String username){
//         User user =userRepository.findByUsername(username).orElseThrow(
//                 () ->  new UsernameNotFoundException("User is not exsist")
//         );
//        UserDetails userDetails =new UserDetails(
//                username,
//                user.getEmail(),
//                user.getName()
//        );
//
//
//      return userDetails;
//      }



    @PreAuthorize(value = "#username == authentication.name")
    @PostMapping (value = "/{username}/image")
         public ResponseEntity setImage(@RequestParam("image") MultipartFile multipartFile,
                                        @PathVariable String username){

           String url = userService.setPhoto(multipartFile,username);
           return ResponseEntity.ok(new ImageModel(url));
     }




}
