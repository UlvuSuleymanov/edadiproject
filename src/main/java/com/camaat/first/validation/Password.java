package com.camaat.first.validation;

import com.camaat.first.validation.validatedBy.IsPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

 @Constraint(validatedBy = IsPasswordValidator.class)
 @Retention(RetentionPolicy.RUNTIME)
 public @interface Password {

        String message() default "invalid password";


        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};


}
