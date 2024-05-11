package az.edadi.back.model.response;

import az.edadi.back.model.UserSummary;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class ConversationRes {

    private Long id;
    //current user's thread
    private Long threadId;
    private String date;
    private UserSummary targetUser;
    private List<MessageResponseModel> messageList;
 }
