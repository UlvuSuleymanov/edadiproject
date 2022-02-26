package az.edadi.back.security.listener.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginEvent {
    private boolean isSuccessfully;
}
