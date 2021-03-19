package com.camaat.first.service.impl;

 import com.camaat.first.entity.Image;
 import com.camaat.first.model.response.UserResponseModel;
import com.camaat.first.repository.UserRepository;
import com.camaat.first.service.ImageService;
 import com.camaat.first.entity.User;
 import com.camaat.first.model.UserPrincipalModel;

import com.camaat.first.security.jwt.JwtBean;
 import com.camaat.first.service.UserService;
import com.camaat.first.utility.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
 import java.util.Optional;
 import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final  PasswordEncoder passwordEncoder;
    private  final UserRepository userRepository;
    private final ImageService imageService;
    private  final JwtBean jwtBean;


    @Autowired
    public UserServiceImp(PasswordEncoder passwordEncoder, UserRepository userRepository, ImageService imageService, JwtBean jwtBean) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.jwtBean = jwtBean;
    }



    @Override
    public UserResponseModel createUserSerponseModel(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        UserResponseModel userResponseModel = new UserResponseModel();



      if(userOptional.isPresent()){
          User user = userOptional.get();
             userResponseModel.setName(user.getName())
                     .setUsername(user.getUsername())
                     .setPostCount(user.getPosts().size())
                     .setImageUrl(ImageServiceImpl.getFullImage(user.getPhotoUrl()))
                     .setCommentCount(user.getComments().size());

         }

        return  userResponseModel;


    }


//    @Override
//    public String setPhoto(MultipartFile multipartFile, String username) {
//
//
//
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User not found with username or email : " + username)
//                );
//
//         String photoName= user.getPhotoUrl();
//         Long id = user.getId();
//         String newName= imageService.setImage(multipartFile,photoName,id);
//              user.setPhotoUrl(newName);
//              userRepository.save(user);
//
//              return ImageUtil.generatePhotoUrl(newName);
//
//
//    }





     @Override
    public  UserPrincipalModel createUserPrincipial(User user) {
        List<GrantedAuthority> authorities = user.getAuthorities().stream().map(authority ->
                new SimpleGrantedAuthority(authority.getPermission())
        ).collect(Collectors.toList());

        return new UserPrincipalModel(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );

    }


}