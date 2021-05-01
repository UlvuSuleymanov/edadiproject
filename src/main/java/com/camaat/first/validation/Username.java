package com.camaat.first.validation;

import com.camaat.first.validation.validatedBy.UsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = UsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Username {
    String message() default "duplicated email";


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}