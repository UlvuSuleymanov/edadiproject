package az.edadi.back.model.response;

import az.edadi.back.constants.type.NotificationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponseModel {

    private String text;
    private String date;
    private NotificationType type;
}
