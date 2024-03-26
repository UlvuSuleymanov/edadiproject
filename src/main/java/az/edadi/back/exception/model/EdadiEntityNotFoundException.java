package az.edadi.back.exception.model;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.utility.Translator;

public class EdadiEntityNotFoundException extends RuntimeException {
    public EdadiEntityNotFoundException(EntityType entityType){
        super(Translator.getTranslation("page_not_found"));
    }
}
