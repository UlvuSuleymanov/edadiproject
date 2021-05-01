package com.camaat.first.validation.validatedBy;

import com.camaat.first.validation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordValidator  implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
