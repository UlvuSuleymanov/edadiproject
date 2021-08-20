package az.edadi.back.validation.validatedBy;

import az.edadi.back.repository.UserRepository;
import az.edadi.back.validation.NotDublicateUsername;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DublicateUsernameValidator implements ConstraintValidator<NotDublicateUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null && s != "")
            return !userRepository.existsByUsername(s.toLowerCase());

        return false;
    }
}
