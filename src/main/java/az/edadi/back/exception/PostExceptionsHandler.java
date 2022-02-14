package az.edadi.back.exception;

import az.edadi.back.exception.model.BadParamsForPostListException;
import az.edadi.back.exception.model.PostAlreadyLikedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostExceptionsHandler {

    @ExceptionHandler({BadParamsForPostListException.class})
    public ResponseEntity handleLoginDetails() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PostAlreadyLikedException.class})
    public ResponseEntity handleAlreadyLikedException() {
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

}
