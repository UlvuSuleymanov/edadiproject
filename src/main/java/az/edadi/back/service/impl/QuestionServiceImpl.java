package az.edadi.back.service.impl;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.entity.app.Question;
import az.edadi.back.entity.auth.User;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.model.response.TopicResponse;
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
    public TopicResponse addQuestion(TopicRequestModel topicRequestModel) {
        log.info("User {} try add new question", AuthUtil.getCurrentUsername());
        Long id = AuthUtil.getCurrentUserId();
//        userEventsRepository.check(UserEvent.ADD_Q);

        User user = userRepository.getById(id);
        Question question =
                Question.builder()
                        .title(topicRequestModel.getTitle())
                        .date(new Date())
                        .user(user)
                        .build();

        Question savedQuestion = questionRepository.saveAndFlush(question);
        log.info("User {} added new question", AuthUtil.getCurrentUsername());
        TopicResponse questionResponseModel = new TopicResponse(savedQuestion);
        return questionResponseModel;
    }

    @Override
//    @Cacheable("questions")
    public List<TopicResponse> getQuestionsList(int page) {

        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("date").descending());

        return questionRepository.findAll(pageable)
                .stream()
                .map(topic -> new TopicResponse(topic))
                .collect(Collectors.toList());
    }

    @Override
    public List<TopicResponse> searchQuestion(String text, int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("date").descending());
        return questionRepository
                .searchUniversityPostsLikeText(text, pageable)
                .stream()
                .map(
                        question -> new TopicResponse(question))
                .collect(Collectors.toList());
    }


    @Override
    public TopicResponse getQuestion(String slug) {
        Long id = SlugUtil.getId(slug);
        Question topic = questionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No Question found with the id " + id)
        );
        return new TopicResponse(topic);
    }

    @Override
    @CacheEvict(cacheNames = "questions", allEntries = true)
    public void deleteQuestion(Long id) {
        log.info("User {} try add new question", AuthUtil.getCurrentUsername());
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        if (question.getUser().getId().equals(AuthUtil.getCurrentUserId())
                || AuthUtil.hasAuthority(UserAuthority.ADMIN_UPDATE))
            questionRepository.delete(question);
        else
            throw new UserAuthorizationException();

        log.info("Question with id {} was deleted", id);

    }
}
