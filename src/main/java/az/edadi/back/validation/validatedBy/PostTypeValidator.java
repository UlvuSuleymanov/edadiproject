package az.edadi.back.validation.validatedBy;

import az.edadi.back.constants.type.PostTypes;
import az.edadi.back.validation.PostType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PostTypeValidator implements ConstraintValidator<PostType, String> {

    @Override
    public boolean isValid(String type, ConstraintValidatorContext context) {
        for (PostTypes postTypes : PostTypes.values())
            if (postTypes.getType().equals(type))
                return true;
        return false;
    }
}