package com.camaat.first.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseModel {
    private String username;
    private String token;
    private Boolean userIsExsist;
    private Boolean authenticated;
    private String photoUrl;
}
