package az.edadi.back.validation.validatedBy;

import az.edadi.back.validation.PostType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VoteTypeValidator implements ConstraintValidator<PostType, String> {

    @Override
    public boolean isValid(String type, ConstraintValidatorContext context) {
        boolean isType = type.equals("post") || type.equals("comment");
        return isType;
    }

}
