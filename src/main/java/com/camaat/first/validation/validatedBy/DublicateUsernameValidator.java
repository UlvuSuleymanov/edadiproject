package com.camaat.first.validation.validatedBy;

 import com.camaat.first.repository.UserRepository;
 import com.camaat.first.validation.NotDublicateUsername;
 import org.springframework.beans.factory.annotation.Autowired;

 import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DublicateUsernameValidator  implements ConstraintValidator<NotDublicateUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s!=null&&s!="")
          return !userRepository.existsByUsername(s);

        return false;
    }
}
