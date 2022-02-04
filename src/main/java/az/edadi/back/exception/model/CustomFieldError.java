package az.edadi.back.exception.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomFieldError {

    private String field;
    private String message;

    public CustomFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
