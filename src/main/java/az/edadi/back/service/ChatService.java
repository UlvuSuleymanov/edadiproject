package az.edadi.back.service;

import az.edadi.back.model.request.MessageRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ChatService {

    void saveMessage(MessageRequestModel messageRequestModel, Long currentUserID) throws JsonProcessingException;

    List<MessageResponseModel> getMessages(int page);

}
