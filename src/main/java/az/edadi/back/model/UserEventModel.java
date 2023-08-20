package az.edadi.back.model;

import az.edadi.back.constants.event.UserEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventModel {
    private Long userId;
    private UserEvent userEvent;
}
