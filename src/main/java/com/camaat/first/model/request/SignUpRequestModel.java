package com.camaat.first.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignUpRequestModel {
     private  String username;
     private String password;
     private  String name;
     private String email;
     private Long uniId;
     private Long specialityId;
     private String newSpecialityText;
     private boolean specialityIsExists;
     private boolean isStudent;
}
