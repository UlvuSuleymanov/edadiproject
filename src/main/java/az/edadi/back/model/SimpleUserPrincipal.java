package az.edadi.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Principal;

@Data
@AllArgsConstructor
public class SimpleUserPrincipal implements Principal {
    private String id;

    @Override
    public String getName() {
        return id;
    }
}
