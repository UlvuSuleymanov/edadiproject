package az.edadi.back.exception;

import az.edadi.back.exception.model.TooManyAttemptException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TooManyAttemptHandler {

    @ExceptionHandler(TooManyAttemptException.class)
    public ResponseEntity handleLoginDetails() {
        return new ResponseEntity("Try again later", HttpStatus.TOO_MANY_REQUESTS);
    }

}
