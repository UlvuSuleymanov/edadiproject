package az.edadi.back.model.response;

import az.edadi.back.model.UserSummary;
import lombok.Data;

@Data
public class ReelsRes {
    private Long id;
    private Long entityId;
    private String type;
    private String title;
    private String date;
    private UserSummary userSummary;
}
