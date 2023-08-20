package az.edadi.back.service.impl;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.entity.Question;
import az.edadi.back.entity.User;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.UserEventModel;
import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.model.response.QuestionResponseModel;
import az.edadi.back.repository.QuestionRepository;
import az.edadi.back.repository.UserEventsRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.QuestionService;
import az.edadi.back.utility.AuthUtil;
import az.edadi.back.utility.SlugUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionServiceImpl implements QuestionService {

    private int DEFAULT_PAGE_SIZE = 20;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final UserEventsRepository userEventsRepository;
     @Override
    @CacheEvict(cacheNames = "questions", allEntries = true)
    public QuestionResponseModel addQuestion(TopicRequestModel topicRequestModel) {
        log.info("User {} try add new question", AuthUtil.getCurrentUsername());
        Long id = AuthUtil.getCurrentUserId();
        userEventsRepository.check(new UserEventModel(id, UserEvent.ADD_QUESTION));

        User user = userRepository.getById(id);
        Question question =
                Question.builder()
                        .title(topicRequestModel.getTitle())
                        .date(new Date())
                        .user(user)
                        .build();

        Question savedQuestion = questionRepository.saveAndFlush(question);
        log.info("User {} added new question", AuthUtil.getCurrentUsername());
        QuestionResponseModel questionResponseModel = new QuestionResponseModel(savedQuestion);
        return questionResponseModel;
    }

    @Override
    @Cacheable("questions")
    public List<QuestionResponseModel> getQuestionsList(int page) {

        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("date").descending());

        return questionRepository.findAll(pageable)
                .stream()
                .map(topic -> new QuestionResponseModel(topic))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponseModel> searchQuestion(String text, int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("date").descending());
       return questionRepository
               .searchUniversityPostsLikeText(text,pageable)
               .stream()
               .map(
                question -> new QuestionResponseModel(question))
               .collect(Collectors.toList());
    }


    @Override
    public QuestionResponseModel getQuestion(String slug) {
        Long id = SlugUtil.getId(slug);
        Question topic = questionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No Question found with the id " + id)
        );
        return new QuestionResponseModel(topic);
    }

    @Override
    @CacheEvict(cacheNames = "questions", allEntries = true)
    public void deleteQuestion(Long id) {
        log.info("User {} try add new question", AuthUtil.getCurrentUsername());
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        if (question.getUser().getId().equals(AuthUtil.getCurrentUserId())
                ||  AuthUtil.hasAuthority(UserAuthority.ADMIN_UPDATE))
            questionRepository.delete(question);
        else
        throw new UserAuthorizationException();

        log.info("Question with id {} was deleted", id);

    }
}
