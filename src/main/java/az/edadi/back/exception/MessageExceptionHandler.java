package az.edadi.back.exception;

import az.edadi.back.exception.model.CreateDublicateThreadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MessageExceptionHandler {

    @ResponseBody
    @ExceptionHandler({CreateDublicateThreadException.class})
    public ResponseEntity handleLoginDetails() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
