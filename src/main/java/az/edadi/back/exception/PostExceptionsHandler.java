package az.edadi.back.exception;

 import az.edadi.back.exception.model.BadParamsForPostListException;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostExceptionsHandler {

    @ResponseBody
    @ExceptionHandler({BadParamsForPostListException.class})
    public ResponseEntity handleLoginDetails() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
