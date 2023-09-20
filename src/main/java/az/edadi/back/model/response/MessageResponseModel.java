package az.edadi.back.model.response;

import az.edadi.back.entity.message.Message;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.AuthUtil;
import az.edadi.back.utility.DateUtil;
import lombok.*;

import java.util.Date;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseModel {
    private Long id;
    private String body;
    private String date;
    private UserSummary author;
    private Long threadId;
    private boolean incoming;

    public MessageResponseModel(Message message) {
        id = message.getId();
        body = message.getText();
        date = DateUtil.getHowLongAgoString(message.getDate());
        author = new UserSummary(message.getUser());
        threadId=message.getThread().getId();
        incoming= !AuthUtil.getCurrentUserId().equals(author.getId());
    }
}
