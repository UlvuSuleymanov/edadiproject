package com.camaat.first.service.impl;

import com.camaat.first.entity.User;
import com.camaat.first.model.UserPrincipalModel;
import com.camaat.first.repository.UserRepository;
import com.camaat.first.service.UserPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserPrincipalServiceImp implements UserPrincipalService {
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserPrincipalModel loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
                );

        return UserServiceImp.createUserPrincipial(user);
    }

     @Override
     @Transactional
    public UserPrincipalModel loadUserById(Long id){
        User user = userRepository.getOne(id);
        return UserServiceImp.createUserPrincipial(user);
    }




    }



