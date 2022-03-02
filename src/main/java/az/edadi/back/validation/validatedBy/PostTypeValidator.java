package az.edadi.back.validation.validatedBy;

import az.edadi.back.constants.PostTypes;
import az.edadi.back.validation.PostType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostTypeValidator implements ConstraintValidator<PostType, String> {

    @Override
    public boolean isValid(String type, ConstraintValidatorContext context) {
        for (PostTypes postTypes : PostTypes.values())
            if (postTypes.getType().equals(type))
                return true;
        return false;
    }
}