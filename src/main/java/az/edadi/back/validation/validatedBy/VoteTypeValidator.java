package az.edadi.back.validation.validatedBy;

import az.edadi.back.constants.VoteTypes;
import az.edadi.back.validation.PostType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class VoteTypeValidator implements ConstraintValidator<PostType, String> {

    @Override
    public boolean isValid(String type, ConstraintValidatorContext context) {
        for (VoteTypes voteTypes : VoteTypes.values())
            if (voteTypes.getType().equals(type))
                return true;
        return false;
    }

}
