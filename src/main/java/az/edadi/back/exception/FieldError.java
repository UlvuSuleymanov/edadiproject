package az.edadi.back.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FieldError {

    private String resource;
    private String field;
    private String code;
    private String message;

    public FieldError(String resource, String field, String code, String message) {
        this.resource = resource;
        this.field = field;
        this.code = code;
        this.message = message;
    }
}
