package az.edadi.back.validation;

import az.edadi.back.validation.validatedBy.DublicateEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Constraint(validatedBy = DublicateEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotDublicateEmail {
    String message() default "duplicated email";


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}