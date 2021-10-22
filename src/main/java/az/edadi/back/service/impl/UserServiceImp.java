package az.edadi.back.service.impl;

import az.edadi.back.constants.PhotoEnum;
import az.edadi.back.entity.User;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.exception.custom.UserNotFoundException;
import az.edadi.back.model.ImageModel;
import az.edadi.back.model.UserPrincipalModel;
import az.edadi.back.model.request.SetSpecialityRequestModel;
import az.edadi.back.model.response.UserResponseModel;
import az.edadi.back.repository.SpecialityRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.FileService;
import az.edadi.back.service.ImageService;
import az.edadi.back.service.UserService;
import az.edadi.back.utility.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final FileService s3Service;
    private final SpecialityRepository specialityRepository;


    @Autowired
    public UserServiceImp(PasswordEncoder passwordEncoder,
                          UserRepository userRepository,
                          ImageService imageService, FileService s3Service, SpecialityRepository specialityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.s3Service = s3Service;
        this.specialityRepository = specialityRepository;
    }


    @Override
    public UserResponseModel getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());

        return new UserResponseModel(user);
    }


    @Override
    public UserPrincipalModel createUserPrincipial(User user) {
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
    public ImageModel setImage(MultipartFile multipartFile) throws IOException {


        Long userId = AuthUtil.getCurrentUserId();
        User user = userRepository.getById(userId);

        String name = UUID.randomUUID().toString();
        ImageModel imageModel = imageService.saveImage(name, multipartFile);

        String oldImageName = user.getImageName();
        user.setImageName(name);
        userRepository.save(user);

        if (!oldImageName.equals(PhotoEnum.USER_DEFAULT_PHOTO.getName())) {
            imageService.deleteFiles(oldImageName);
        }

        return imageModel;

    }

    @Override
    public void setSpeciality(SetSpecialityRequestModel setSpecialityRequestModel) {
        Optional<Speciality> speciality = specialityRepository.findById(setSpecialityRequestModel.getSpecialityId());
        Optional<User> user = userRepository.findById(AuthUtil.getCurrentUserId());

       if(user.isPresent() && speciality.isPresent()){
            user.get().setSpeciality(speciality.get());
            userRepository.save(user.get());
       }
    }


}