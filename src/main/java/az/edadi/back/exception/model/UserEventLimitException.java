package az.edadi.back.exception.model;

import az.edadi.back.constants.event.UserEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
 public class UserEventLimitException  extends RuntimeException{
  private UserEvent userEvent;
  public UserEventLimitException(UserEvent userEvent){
    super("mmm");
    this.userEvent=userEvent;
  }
}
