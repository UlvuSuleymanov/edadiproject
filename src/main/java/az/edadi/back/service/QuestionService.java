package az.edadi.back.service;

import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.model.response.TopicResponse;

import java.util.List;

public interface QuestionService {
    TopicResponse addQuestion(TopicRequestModel topicRequestModel);
    List<TopicResponse> getQuestionsList(int page);
    List<TopicResponse> searchQuestion(String text, int page);
    TopicResponse getQuestion(String slug);
    void deleteQuestion(Long id);
}
