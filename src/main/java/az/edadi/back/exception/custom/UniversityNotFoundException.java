package az.edadi.back.exception.custom;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UniversityNotFoundException extends NotFoundException {

    public UniversityNotFoundException(String msg) {
        super(msg);
    }
}
