package az.edadi.back.exception;
import az.edadi.back.exception.model.UserEventLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class UserEventLimitExceptionHandler {
    @ExceptionHandler({UserEventLimitException.class})
    public ResponseEntity<String> handleUserEventExceptions(UserEventLimitException userEventLimitException) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userEventLimitException.getMessage());
    }
}
