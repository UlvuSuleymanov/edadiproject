package az.edadi.back.service;

import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MessageService {

    MessageResponseModel sendChatMessage(MessageRequestModel messageRequestModel);

    List<MessageResponseModel> getMessages(Long rec);
}
