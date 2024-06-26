package az.edadi.back.service.impl.auth;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.app.FileItem;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.exception.model.EdadiEntityNotFoundException;
import az.edadi.back.model.UserPrincipalModel;
import az.edadi.back.model.request.SetSpecialityRequestModel;
import az.edadi.back.model.request.UpdateUserImageReq;
import az.edadi.back.model.response.SignInResponseModel;
import az.edadi.back.model.response.UserResponseModel;
import az.edadi.back.repository.FileItemRepository;
import az.edadi.back.repository.SpecialityRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.ImageService;
import az.edadi.back.service.JwtService;
import az.edadi.back.service.UserService;
import az.edadi.back.utility.AuthUtil;
import io.minio.errors.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final ImageService imageService;
    private final SpecialityRepository specialityRepository;
    private final JwtService jwtService;
    private final FileItemRepository fileItemRepository;

    @Override
    public UserResponseModel getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EdadiEntityNotFoundException(EntityType.USER));
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
    public String updateUserImage(UpdateUserImageReq updateUserImageReq) {
        FileItem fileItem = fileItemRepository.findByUuid(updateUserImageReq.getId()).orElseThrow(
                ()->new EdadiEntityNotFoundException(EntityType.FILE));
        User currentUser = userRepository.findById(AuthUtil.getCurrentUserId()).orElseThrow(
                ()->new EdadiEntityNotFoundException(EntityType.USER));
        currentUser.setPicture(fileItem.getUrl());
        fileItem.setUsed(true);
        fileItem.setUser(currentUser);
        userRepository.save(currentUser);
        fileItemRepository.save(fileItem);
        return fileItem.getUrl();
    }

    @Override
    public void setSpeciality(SetSpecialityRequestModel setSpecialityRequestModel) {
        Optional<Speciality> speciality = specialityRepository.findById(setSpecialityRequestModel.getSpecialityId());
        Optional<User> user = userRepository.findById(AuthUtil.getCurrentUserId());

        if (user.isPresent() && speciality.isPresent()) {
            user.get().setSpeciality(speciality.get());
            userRepository.save(user.get());
        }
    }

    @Override
    public SignInResponseModel getCurrentUser() {
        Long id = AuthUtil.getCurrentUserId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EdadiEntityNotFoundException(EntityType.USER));
        return new SignInResponseModel(user, jwtService.getTokenResponse(user));
    }


}