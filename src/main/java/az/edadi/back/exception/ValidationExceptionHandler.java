package az.edadi.back.exception;

import az.edadi.back.exception.model.CustomFieldError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public List<CustomFieldError> processValidationError(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<CustomFieldError> customFieldErrors = new ArrayList<>();
        CustomFieldError validationExceptionModel = new CustomFieldError();

        for (FieldError error : fieldErrors) {
            customFieldErrors.add(new CustomFieldError(error.getField(), error.getDefaultMessage()));
        }

        return customFieldErrors;
    }

}