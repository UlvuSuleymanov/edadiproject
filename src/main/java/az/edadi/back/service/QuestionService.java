package az.edadi.back.service;

import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.model.response.QuestionResponseModel;

import java.util.List;

public interface QuestionService {

    QuestionResponseModel addQuestion(TopicRequestModel topicRequestModel);

    List<QuestionResponseModel> getQuestionsList(int page);

    List<QuestionResponseModel> searchQuestion(String text,int page);

    QuestionResponseModel getQuestion(String slug);

    void deleteQuestion(Long id);

}
