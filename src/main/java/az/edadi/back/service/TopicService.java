package az.edadi.back.service;

import az.edadi.back.model.SummaryModel;

import java.util.List;

public interface TopicService {

    SummaryModel addTopic(String title);

    List<SummaryModel> getTopicList(int page);

    SummaryModel getTopic(Long id);

}
