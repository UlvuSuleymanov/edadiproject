package com.camaat.first.service.impl;

  import com.camaat.first.model.ImageResponseModel;
 import com.camaat.first.model.response.UserResponseModel;
import com.camaat.first.repository.UserRepository;
import com.camaat.first.service.ImageService;
 import com.camaat.first.entity.User;
 import com.camaat.first.model.UserPrincipalModel;

  import com.camaat.first.service.UserService;
 import com.camaat.first.utility.AuthUtil;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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


    @Autowired
    public UserServiceImp(PasswordEncoder passwordEncoder,
                          UserRepository userRepository,
                          ImageService imageService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.imageService = imageService;
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

    @Override
    public ImageResponseModel setImage(MultipartFile multipartFile) {
        Long userId = AuthUtil.getCurrentUserId();

        User user = userRepository.getOne(userId);

        String id = imageService.setImage(multipartFile,true);

        user.setPhotoUrl(id);

        ImageResponseModel imageResponseModel =new ImageResponseModel();
        imageResponseModel.setUrl(ImageServiceImpl.getThumbImage(id));
        imageResponseModel.setFullImageUrl(ImageServiceImpl.getFullImage(id));

        return imageResponseModel;
     }


}