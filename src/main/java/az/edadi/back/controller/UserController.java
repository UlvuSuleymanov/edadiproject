package az.edadi.back.controller;
  import az.edadi.back.model.response.ImageResponseModel;
  import az.edadi.back.repository.UserRepository;
  import az.edadi.back.service.UserService;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.*;
  import org.springframework.web.multipart.MultipartFile;

  import java.io.IOException;


@RestController
@RequestMapping("api/user/")
  public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

     @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
         this.userRepository = userRepository;
         this.userService = userService;
     }


     @GetMapping("{username}")
     ResponseEntity getUserDetails(@PathVariable String username){
     return  ResponseEntity.ok(userService.getUserByUsername(username));
     }


    @PostMapping("{username}/image")
     public ResponseEntity setImage(@RequestParam("image") MultipartFile multipartFile,
                                    @PathVariable String username) throws IOException {

        ImageResponseModel responseModel= userService.setImage(multipartFile);
        return ResponseEntity.ok(responseModel);
    }




}
