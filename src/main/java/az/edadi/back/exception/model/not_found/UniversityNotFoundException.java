package az.edadi.back.exception.model.not_found;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UniversityNotFoundException extends RuntimeException {
    public UniversityNotFoundException(String msg) {
        super(msg);
    }
}
