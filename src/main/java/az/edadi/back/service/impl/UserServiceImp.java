package az.edadi.back.service.impl;

  import az.edadi.back.config.S3Bean;
  import az.edadi.back.entity.User;
  import az.edadi.back.exception.UserNotFoundException;
  import az.edadi.back.service.FileService;
  import az.edadi.back.service.ImageService;
  import az.edadi.back.service.UserService;
  import az.edadi.back.model.response.ImageResponseModel;
 import az.edadi.back.model.response.UserResponseModel;
import az.edadi.back.repository.UserRepository;
  import az.edadi.back.model.UserPrincipalModel;

  import az.edadi.back.utility.AuthUtil;
  import az.edadi.back.utility.PhotoUtil;
  import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
 import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

  import java.io.File;
  import java.io.IOException;
  import java.util.List;
  import java.util.UUID;
  import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final  PasswordEncoder passwordEncoder;
    private  final UserRepository userRepository;
    private final ImageService imageService;
    private final FileService s3Service;


    @Autowired
    public UserServiceImp(PasswordEncoder passwordEncoder,
                          UserRepository userRepository,
                          ImageService imageService, FileService s3Service) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.s3Service = s3Service;
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
    public ImageResponseModel setImage(MultipartFile multipartFile) throws IOException {

        Long userId = AuthUtil.getCurrentUserId();
        User user = userRepository.getOne(userId);

        String key= UUID.randomUUID().toString();

        File orginalImage = s3Service.convertMultiPartToFile(multipartFile);
        s3Service.save(key,orginalImage);

        File thumbImage =imageService.getSmallPicture(orginalImage);
        s3Service.save("thumb"+key, thumbImage);

        orginalImage.delete();
        thumbImage.delete();





        user.setPhotoUrl(key);
        userRepository.save(user);

        /**/
        ImageResponseModel imageResponseModel =new ImageResponseModel();
        imageResponseModel.setUrl(PhotoUtil.getFullPhotoUrl(key));
        imageResponseModel.setFullPhotoUrl(PhotoUtil.getThumbPhotoUrl(key));

        return imageResponseModel;
     }





}