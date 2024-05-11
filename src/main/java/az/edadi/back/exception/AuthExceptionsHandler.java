package az.edadi.back.exception;

import az.edadi.back.exception.model.UsernameOrPasswordNotCorrectException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class AuthExceptionsHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseBody
    public ResponseEntity handleLoginDetails() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({UsernameOrPasswordNotCorrectException.class})
    @ResponseBody
    public ResponseEntity handleConstraintViolation() {
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler({ExpiredJwtException.class})
    @ResponseBody
    public ResponseEntity expiredJwtExceptionHandler() {
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

}   
