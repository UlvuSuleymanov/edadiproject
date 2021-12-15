package az.edadi.back.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MessageRequestModel implements Serializable {
    private String message;
    private String to;
}
