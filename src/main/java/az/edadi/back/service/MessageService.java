package az.edadi.back.service;

import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MessageService {

    MessageResponseModel sendChatMessage(MessageRequestModel messageRequestModel) throws JsonProcessingException;

    List<MessageResponseModel> getMessages(Long rec);
}
