package az.edadi.back.service;

import az.edadi.back.model.request.ConversationReq;
import az.edadi.back.model.response.ConversationRes;
import lombok.Data;

import java.util.Date;
import java.util.List;

public interface ConversationService {
    ConversationRes getConversationByUsername(ConversationReq conversationReq);

    ConversationRes getConversationById(Long id);

    List<ConversationRes> getConversationList(int page);

    void updateConversationLastMessageDate(Long conversationId);
}
