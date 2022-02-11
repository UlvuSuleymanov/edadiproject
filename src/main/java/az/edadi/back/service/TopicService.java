package az.edadi.back.service;

import az.edadi.back.model.response.TopicResponseModel;

import java.util.List;

public interface TopicService {

    TopicResponseModel addTopic(String title);

    List<TopicResponseModel> getTopicList(int page);

    TopicResponseModel getTopic(String slug);

}
