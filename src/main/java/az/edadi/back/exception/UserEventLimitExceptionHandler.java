package az.edadi.back.exception;

import az.edadi.back.exception.model.UserEventLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserEventLimitExceptionHandler {
    @ResponseBody
    @ExceptionHandler({UserEventLimitException.class})
    public ResponseEntity handleLoginDetails(UserEventLimitException userEventLimitException) {
        return new ResponseEntity("user cannot perform this action temporarily. Event: "+userEventLimitException.userEvent.getEvent(), HttpStatus.LOCKED);
    }

}
