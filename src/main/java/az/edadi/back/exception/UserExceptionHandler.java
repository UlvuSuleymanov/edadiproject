package az.edadi.back.exception;

import az.edadi.back.exception.model.UserAuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({UserAuthorizationException.class})
    public ResponseEntity<String> handleUserAuthorizationExceptions() {
        return new ResponseEntity("User not authorized to perform this action.",HttpStatus.UNAUTHORIZED);
    }
}
