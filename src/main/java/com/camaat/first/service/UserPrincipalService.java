package com.camaat.first.service;

 import com.camaat.first.model.UserPrincipalModel;
 import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserPrincipalService extends UserDetailsService {
   public UserPrincipalModel loadUserById(Long id);
}
