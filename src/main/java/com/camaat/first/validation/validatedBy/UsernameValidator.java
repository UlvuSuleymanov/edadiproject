package com.camaat.first.validation.validatedBy;

import com.camaat.first.repository.UserRepository;
import com.camaat.first.utility.PatternFactory;
import com.camaat.first.validation.Username;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
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
