package az.edadi.back.exception;

import az.edadi.back.exception.model.CustomFieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

    @ExceptionHandler({ TransactionSystemException.class })
    public List<CustomFieldError>handleConstraintViolation(Exception ex, WebRequest request) {
        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        if (cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            System.out.println("aasdsaddas");

       }
        CustomFieldError validationExceptionModel = new CustomFieldError();
        return Arrays.asList(validationExceptionModel);
    }
}