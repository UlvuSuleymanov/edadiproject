package az.edadi.back.validation.validatedBy;

import az.edadi.back.repository.UserRepository;
import az.edadi.back.validation.NotDublicateEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class DublicateEmailValidator implements ConstraintValidator<NotDublicateEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s!=null&&s!="")
          return !userRepository.existsByEmail(s.toLowerCase());

        return false;
    }
}
