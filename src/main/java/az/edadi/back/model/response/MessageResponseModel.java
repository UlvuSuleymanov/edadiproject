package az.edadi.back.model.response;

import az.edadi.back.entity.message.Message;
import az.edadi.back.model.UserSummary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageResponseModel {
    private Long id;
    private String body;
    private Date date;
    private UserSummary author;
    private Long threadId;

    public MessageResponseModel(Message message) {
        id = message.getId();
        body = message.getText();
        date = message.getDate();
        author = new UserSummary(message.getUser());
        threadId=message.getThread().getId();
    }
}
