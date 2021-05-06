package az.edadi.back.service;

 import az.edadi.back.model.UserPrincipalModel;
 import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserPrincipalService extends UserDetailsService {
   public UserPrincipalModel loadUserById(Long id);
}
