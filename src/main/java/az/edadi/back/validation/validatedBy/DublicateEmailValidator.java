package az.edadi.back.validation.validatedBy;

import az.edadi.back.repository.UserRepository;
import az.edadi.back.validation.NotDublicateEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
