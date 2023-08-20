package az.edadi.back.exception.model;

import az.edadi.back.constants.event.UserEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserEventLimitException  extends RuntimeException{
  public UserEvent userEvent;
}
