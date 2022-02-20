package az.edadi.back.validation;

 import az.edadi.back.validation.validatedBy.PasswordValidator;
 import az.edadi.back.validation.validatedBy.PostTypeValidator;

 import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = PostTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PostType {

    String message() default "There is no post of this type.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}