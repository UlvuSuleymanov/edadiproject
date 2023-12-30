package az.edadi.back.service;

import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface MessageService {

    MessageResponseModel sendMessageToRoom(MessageRequestModel messageRequestModel,Long currentUser) throws JsonProcessingException;

    List<MessageResponseModel> getMessages(Long roomId, int page);
}
