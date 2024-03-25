package az.edadi.back.exception.model;

import az.edadi.back.constants.event.UserEvent;
public class UserEventLimitException extends RuntimeException{
  public UserEventLimitException(UserEvent userEvent){
    super(userEvent.getEvent());
  }
}
