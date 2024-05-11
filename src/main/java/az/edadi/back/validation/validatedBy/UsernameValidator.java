package az.edadi.back.validation.validatedBy;

import az.edadi.back.utility.factory.PatternFactory;
import az.edadi.back.validation.Username;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UsernameValidator implements ConstraintValidator<Username, String> {

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        Pattern pattern = PatternFactory.getUsernamePattern();

        Matcher matcher = pattern.matcher(username);

        return matcher.find();

    }

}
