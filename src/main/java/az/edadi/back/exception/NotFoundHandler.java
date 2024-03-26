package az.edadi.back.exception;

import az.edadi.back.exception.model.EdadiEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundHandler {

    @ResponseBody
    @ExceptionHandler({EdadiEntityNotFoundException.class})
    public ResponseEntity<String> handleLoginDetails(EdadiEntityNotFoundException edadiEntityNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(edadiEntityNotFoundException.getMessage());
    }

}
