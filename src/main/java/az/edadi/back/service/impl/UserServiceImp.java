package az.edadi.back.service.impl;

  import az.edadi.back.entity.User;
  import az.edadi.back.exception.UserNotFoundException;
  import az.edadi.back.service.ImageService;
  import az.edadi.back.service.UserService;
  import az.edadi.back.model.response.ImageResponseModel;
 import az.edadi.back.model.response.UserResponseModel;
import az.edadi.back.repository.UserRepository;
  import az.edadi.back.model.UserPrincipalModel;

  import az.edadi.back.utility.AuthUtil;
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
    public UserResponseModel getUserByUsername(String username) {
      User user = userRepository.findByUsername(username)
              .orElseThrow(()->new UserNotFoundException());

      return new UserResponseModel(user);
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
        userRepository.save(user);

        ImageResponseModel imageResponseModel =new ImageResponseModel();
        imageResponseModel.setUrl(ImageServiceImpl.getThumbImage(id));
        imageResponseModel.setFullImageUrl(ImageServiceImpl.getFullImage(id));

        return imageResponseModel;
     }


}