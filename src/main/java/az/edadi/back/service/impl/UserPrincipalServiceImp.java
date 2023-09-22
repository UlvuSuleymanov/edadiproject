package az.edadi.back.service.impl;

import az.edadi.back.entity.auth.User;
import az.edadi.back.service.UserPrincipalService;
import az.edadi.back.service.UserService;
import az.edadi.back.model.UserPrincipalModel;
import az.edadi.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalServiceImp implements UserPrincipalService {

    private  final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserPrincipalServiceImp(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override

    public UserPrincipalModel loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
                );

        return userService.createUserPrincipial(user);
    }

     @Override

     public UserPrincipalModel loadUserById(Long id){
        User user = userRepository.getOne(id);
        return userService.createUserPrincipial(user);
    }




    }



