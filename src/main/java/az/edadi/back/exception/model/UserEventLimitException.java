package az.edadi.back.exception.model;

import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.utility.Translator;

public class UserEventLimitException extends RuntimeException{
  public UserEventLimitException(UserEvent userEvent){
    super(Translator.getTranslation("operation_limit_error"));
  }
}
