package az.edadi.back.validation.validatedBy;

import az.edadi.back.repository.UserRepository;
import az.edadi.back.validation.NotDublicateUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


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
