package az.edadi.back.validation.validatedBy;


import az.edadi.back.validation.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<Password, String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && s != "" && s.length() > 5;
    }
}