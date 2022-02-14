package az.edadi.back.exception;

import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.exception.model.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseBody
    public ResponseEntity handleLoginDetails() {
        return new ResponseEntity("User not found",HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({UserAuthorizationException.class})
    public ResponseEntity handleUserAuthorizationExceptions() {
        return new ResponseEntity("User not authorized to perform this action.",HttpStatus.UNAUTHORIZED);
    }
}
