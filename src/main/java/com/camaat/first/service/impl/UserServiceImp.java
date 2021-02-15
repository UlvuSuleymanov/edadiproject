package com.camaat.first.service.impl;

import com.camaat.first.model.response.SignInResponseModel;
import com.camaat.first.model.response.UserResponseModel;
import com.camaat.first.repository.UserRepository;
import com.camaat.first.service.ImageService;
import com.camaat.first.utility.UserEnum;
import com.camaat.first.entity.User;
import com.camaat.first.entity.UserAuthority;
import com.camaat.first.model.UserPrincipalModel;
import com.camaat.first.model.request.SignInRequestModel;
import com.camaat.first.model.request.SignUpRequestModel;
import com.camaat.first.security.jwt.JwtBean;
import com.camaat.first.security.jwt.JwtProvider;
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
    public User     userBuilder(SignUpRequestModel signUpRequestModel) {
        User user = new User();
        user.setName(signUpRequestModel.getName());
        user.setEmail(signUpRequestModel.getEmail());
        user.setUsername(signUpRequestModel.getUsername());
        user.setPhotoUrl(UserEnum.DEFAULT_USER_IMAGE_NAME.getImageName());
        user.getAuthorities().add(UserAuthority.USER_READ);
        user.getAuthorities().add(UserAuthority.ADMIN_UPDATE);
        user.setPassword(passwordEncoder.encode(signUpRequestModel.getPassword()));
        return user;

    }

    @Override
    public UserResponseModel createUserSerponseModel(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + username)
                );
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setName(user.getName())
                .setImageUrl(ImageUtil.generatePhotoUrl(user.getPhotoUrl()))
                .setUsername(user.getUsername())
                .setPostCount(user.getPosts().size())
                .setCommentCount(user.getComments().size());
        return  userResponseModel;


    }


    @Override
    public String setPhoto(MultipartFile multipartFile, String username) {



        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + username)
                );

         String photoName= user.getPhotoUrl();
         Long id = user.getId();
         String newName= imageService.setImage(multipartFile,photoName,id);
              user.setPhotoUrl(newName);
              userRepository.save(user);

              return ImageUtil.generatePhotoUrl(newName);


    }

    @Override
    public SignInResponseModel login(SignInRequestModel signInRequestModel) {
        JwtProvider jwtProvider = new JwtProvider(jwtBean);
        String username = signInRequestModel.getUsername();
        String password = signInRequestModel.getPassword();
        User user;


        if(userRepository.existsByUsername(username) || userRepository.existsByEmail(username))
        {
            user = userRepository.findByUsernameOrEmail(username,username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found with username or email : " + username)
                    );

        }
        else {
            return  new SignInResponseModel("","",false,false,"");


        }


        if(!passwordEncoder.matches(password,user.getPassword())){
            return  new SignInResponseModel("","",true,false,"");

        }

        String token = jwtProvider.jwtBuilder(user.getUsername(),user.getId(), user.getAuthorities());

        return  new SignInResponseModel(user.getUsername(),token,true,true, ImageUtil.generatePhotoUrl(user.getPhotoUrl()) );












    }

    @Override
    public boolean checkPassword(String password) {

    if(password!=null) {
        if (password.trim().length() < 3)
            return false;
        else {
            return true;
        }


    }
        return false;
    }


    public static UserPrincipalModel createUserPrincipial(User user) {
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