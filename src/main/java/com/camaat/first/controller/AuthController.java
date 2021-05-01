package com.camaat.first.controller;

  import com.camaat.first.model.request.SignInRequestModel;
  import com.camaat.first.model.request.SignUpRequestModel;
  import com.camaat.first.model.response.SignInResponseModel;
  import com.camaat.first.model.response.SignUpResponseModel;
  import com.camaat.first.service.AuthenticationService;
  import com.camaat.first.service.UserService;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.ResponseEntity;
  import org.springframework.validation.annotation.Validated;
  import org.springframework.web.bind.annotation.*;

  import javax.validation.Valid;


@RestController
@RequestMapping(value = "api/auth")
 public class AuthController {


    private final UserService userService;
    private  final AuthenticationService authenticationService;

    @Autowired
    public AuthController( AuthenticationService authenticationService,UserService userService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }



    @PostMapping(value = "/signin")
    public ResponseEntity<?> login(@RequestBody SignInRequestModel signInRequestModel){
  //  SignInResponseModel signUpResponseModel=   authenticationService.signIn(signInRequestModel);

        SignInResponseModel signUpResponseModel= null;

        return  ResponseEntity.ok(signUpResponseModel);

     }


    @PostMapping(value = "/signup")
    public ResponseEntity addUser( @Validated @RequestBody final SignUpRequestModel signUpRequestModel){


      SignUpResponseModel signUpResponseModel = new SignUpResponseModel();
      signUpResponseModel = authenticationService.signUp(signUpRequestModel);

        return   ResponseEntity.ok(signUpResponseModel);
    }



}
