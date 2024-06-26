package az.edadi.back.model.response;

import az.edadi.back.entity.message.Message;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.AuthUtil;
import az.edadi.back.utility.DateUtil;
import lombok.*;


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
    private Long conversationId;
    private boolean incoming;

    public MessageResponseModel(Message message) {
        id = message.getId();
        body = message.getText();
        date = DateUtil.getHowLongAgoString(message.getDateCreated());
        author = new UserSummary(message.getThread().getUser());
        conversationId = message.getConversation().getId();
        incoming = !AuthUtil.getCurrentUserId().equals(author.getId());
    }
}
