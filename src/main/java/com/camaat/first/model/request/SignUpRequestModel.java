package com.camaat.first.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestModel {
     private  String username;
     private String password;
     private  String name;
     private String email;
}
