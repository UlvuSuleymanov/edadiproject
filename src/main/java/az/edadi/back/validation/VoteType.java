package az.edadi.back.validation;

import az.edadi.back.validation.validatedBy.VoteTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = VoteTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface VoteType {

    String message() default "There is no vote of this type.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}