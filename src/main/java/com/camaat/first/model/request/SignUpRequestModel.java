package com.camaat.first.model.request;

import com.camaat.first.validation.Password;
import com.camaat.first.validation.Username;
import com.camaat.first.validation.NotDublicateUsername;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("newUser")
 public class SignUpRequestModel {
     @NotBlank
     @NotDublicateUsername(message = "Username is exists")
     @Username(message = "invalid username string")
     private  String username;

     @Size(min = 6,max = 20)
     private String password;


     @Size(min = 3,max = 35)
     private  String name;

     @NotBlank
     @Email
     private String email;

     private Long uniId;

     private Long specialityId;

     private String newSpecialityText;
     private Boolean specialityIsExists;
     private Boolean isStudent;
}
