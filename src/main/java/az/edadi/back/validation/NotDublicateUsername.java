package az.edadi.back.validation;

import az.edadi.back.validation.validatedBy.DublicateUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Constraint(validatedBy = DublicateUsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotDublicateUsername {
    String message() default "İstifadəçi adı  mövcuddur";


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}