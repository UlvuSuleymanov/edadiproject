package com.camaat.first.service.impl;

import com.camaat.first.entity.*;
import com.camaat.first.entity.university.SpecialityOffer;
import com.camaat.first.entity.university.UniSpeciality;
import com.camaat.first.entity.university.University;
import com.camaat.first.model.request.SignInRequestModel;
import com.camaat.first.model.request.SignUpRequestModel;
import com.camaat.first.model.response.SignInResponseModel;
import com.camaat.first.model.response.SignUpResponseModel;
import com.camaat.first.repository.*;
import com.camaat.first.security.jwt.JwtBean;
import com.camaat.first.security.jwt.JwtProvider;
import com.camaat.first.service.AuthenticationService;
import com.camaat.first.service.SpecialityService;
 import com.camaat.first.service.UserService;
import com.camaat.first.utility.ImageUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

 import java.util.Optional;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtBean jwtBean;
    private final SpecialityRepository  specialityRepository;
    private final UniversityRepository universityRepository;
    private final SpecialityService specialityService;
    private final SpecialityOfferRepository specialityOfferRepository;
    private final UniSpecialityRepository uniSpecialityRepository;

    private  User user;

    public AuthenticationServiceImpl(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder, JwtBean jwtBean, SpecialityRepository specialityRepository, UniversityRepository universityRepository, SpecialityService specialityService, SpecialityOfferRepository specialityOfferRepository, UniSpecialityRepository uniSpecialityRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtBean = jwtBean;
        this.specialityRepository = specialityRepository;
        this.universityRepository = universityRepository;
        this.specialityService = specialityService;
        this.specialityOfferRepository = specialityOfferRepository;
        this.uniSpecialityRepository = uniSpecialityRepository;
    }



    @Override
    public SignUpResponseModel signUp(final SignUpRequestModel signUpRequestModel) {

        System.out.println(signUpRequestModel.toString());

          user = new User();
        SignUpResponseModel signUpResponseModel =  new SignUpResponseModel();


        signUpResponseModel.setEmailIsValid(setEmail(signUpRequestModel.getEmail()))
                .setUsernameIsValid(setUsername(signUpRequestModel.getUsername()))
                .setNameIsValid(setName(signUpRequestModel.getName()))
                .setPasswordIsValid(setPassword(signUpRequestModel.getPassword()));

        if(signUpRequestModel.getIsStudent()){
            signUpResponseModel.setUniversityIsValid(setUniversity(signUpRequestModel.getUniId()));

            if(signUpRequestModel.getSpecialityIsExists()){
                signUpResponseModel.setSpecialityIsValid(setSpeciality(signUpRequestModel.getSpecialityId()));
            }

        }






        if(signUpResponseModel.isEmailIsValid()
           &&signUpResponseModel.isUsernameIsValid()
           &&signUpResponseModel.isNameIsValid()
           &&signUpResponseModel.isPasswordIsValid())
        {

            if(signUpRequestModel.getIsStudent()&& !signUpRequestModel.getSpecialityIsExists())
            {
                SpecialityOffer speciality = new SpecialityOffer();
                speciality.setName(signUpRequestModel.getNewSpecialityText());
                speciality.setAuthor(user.getUsername());
                speciality.setUniId(signUpRequestModel.getUniId());
                specialityOfferRepository.save(speciality);
            }

                 user.setPhotoUrl("default");
                 user.getAuthorities().add(UserAuthority.USER_READ);
                 userRepository.save(user);
                return signUpResponseModel.setNewUserIsValid(true);


        }


        return signUpResponseModel.setNewUserIsValid(false);

    }


    @Override
    public boolean setUsername(String username) {
        boolean isGoodUsername=true;

        Optional<String> optional = Optional.ofNullable(username);


        if(username!=null&&username.isEmpty()&& username.length()>40)
        {
         return false;
        }

        isGoodUsername = !userRepository.existsByUsername(username);

        if(isGoodUsername)
          user.setUsername(username);


        return isGoodUsername;
    }



    @Override
    public boolean setPassword(String password) {

        boolean isGood=true;

        if(password!=null) {

            if (password.trim().length() < 6)
                isGood= false;
            else {
                isGood=true;
            }


        }
        else{
            isGood=false;
        }


        if(isGood)
        {
            user.setPassword(passwordEncoder.encode(password));

        }




        return isGood;
    }



    @Override
    public boolean setEmail(String email) {
      boolean isGoodEmail=false;

      Optional<String> emailOptional = Optional.ofNullable(email);

        System.out.println("aeeee"+emailOptional.isEmpty());
      if(emailOptional.isPresent()) {
          isGoodEmail = !userRepository.existsByEmail(email);
      }

         if(isGoodEmail)
           user.setEmail(email);


      return isGoodEmail;

    }

    @Override
    public boolean setName(String name) {
        Optional<String> nameOptional = Optional.ofNullable(name);

        if(nameOptional.isPresent() && name.length()>2)
        {  user.setName(name);
          return true;
        }
        return false;
    }

    @Override
    public boolean setUniversity(Long uniId) {
        Optional<University> universityOptional = universityRepository.findById(uniId);

        System.out.println("line178"+universityOptional.isPresent());
        if(universityOptional.isPresent())
        {
            user.setUniversity(universityOptional.get());
            return true;
        }
        return false;
    }


    @Override
    public boolean setSpeciality(Long specialityId) {
        Optional<Long> id = Optional.ofNullable(specialityId);

      if(!id.isPresent())
        {
            return false;
        }

        Optional<UniSpeciality> specialityOptional=uniSpecialityRepository.findById(specialityId);

        boolean isGood=false;
        if(specialityOptional.isPresent()){

           isGood= specialityOptional.get().getUniversity().getId().longValue()==user.getUniversity().getId().longValue();

        }

       if(isGood){
           user.setUniSpeciality(specialityOptional.get());
        }

       return isGood;

    }











    @Override
    public SignInResponseModel signIn(SignInRequestModel signInRequestModel) {
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



//    @Override
//    public User userBuilder(SignUpRequestModel signUpRequestModel) {
//
//        if(signUpRequestModel.isStudent()){
//
//        }
//        User user = new User();
//        user.setName(signUpRequestModel.getName());
//        user.setEmail(signUpRequestModel.getEmail());
//        user.setUsername(signUpRequestModel.getUsername());
//        user.setPhotoUrl(UserEnum.DEFAULT_USER_IMAGE_NAME.getImageName());
//        user.getAuthorities().add(UserAuthority.USER_READ);
//
//        user.setPassword(passwordEncoder.encode(signUpRequestModel.getPassword()));
//        return user;
//
//    }



}
