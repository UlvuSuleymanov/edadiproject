package az.edadi.back.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;


import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
public class AuthExceptionsHandler  {

@ExceptionHandler({EntityNotFoundException.class})
@ResponseBody
public ResponseEntity handleLoginDetails(){
    return new ResponseEntity(HttpStatus.NOT_FOUND);
}


 @ExceptionHandler({UsernameOrPasswordNotCorrectException.class})
 @ResponseBody
 public ResponseEntity handleConstraintViolation(){
      return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
