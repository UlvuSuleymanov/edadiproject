package az.edadi.back.service;

import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.model.response.TopicResponseModel;

import java.util.List;

public interface TopicService {

    TopicResponseModel addTopic(TopicRequestModel topicRequestModel);

    List<TopicResponseModel> getTopicList(int page);

    TopicResponseModel getTopic(String slug);

    void deleteTopic(Long id);

}
