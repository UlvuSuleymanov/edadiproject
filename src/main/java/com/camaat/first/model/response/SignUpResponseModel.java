package com.camaat.first.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseModel {
     private boolean usernameIsValid;
     private boolean emailIsValid;
     private boolean passwordIsValid;
     private boolean nameIsValid;
     private boolean universityIsValid;
     private boolean specialityIsValid;
     private  boolean newUserIsValid;
}
