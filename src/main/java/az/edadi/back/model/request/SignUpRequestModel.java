package az.edadi.back.model.request;

import az.edadi.back.validation.NotDublicateEmail;
import az.edadi.back.validation.NotDublicateUsername;
import az.edadi.back.validation.Username;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
 import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("newUser")
 public class SignUpRequestModel {

     @NotDublicateUsername(message = "Username is exists")
     @Username(message = "invalid username string")
     private  String username;

     @Size(min = 6,max = 20)
     private String password;


     @Size(min = 3,max = 35)
     private  String name;

     @Email
     @NotDublicateEmail
     private String email;


}
