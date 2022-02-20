package az.edadi.back.validation.validatedBy;

import az.edadi.back.validation.PostType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostTypeValidator implements ConstraintValidator<PostType, String> {

    @Override
    public boolean isValid(String type, ConstraintValidatorContext context) {
        boolean isType = type.equals("university") || type.equals("speciality") || type.equals("topic");
        return isType;
    }

}
