package az.edadi.back.service.impl;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.entity.Question;
import az.edadi.back.entity.User;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.model.response.QuestionResponseModel;
import az.edadi.back.repository.QuestionRepository;
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

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Override
    @CacheEvict(cacheNames = "questions", allEntries = true)
    public QuestionResponseModel addQuestion(TopicRequestModel topicRequestModel) {
        log.info("User {} try add new question", AuthUtil.getCurrentUsername());

        User user = userRepository.getById(AuthUtil.getCurrentUserId());
        Question question = new Question();
        question.setTitle(topicRequestModel.getTitle());
        question.setDate(new Date());
        question.setUser(user);
        Question savedQuestion = questionRepository.save(question);
        log.info("User {} added new question", AuthUtil.getCurrentUsername());

        return new QuestionResponseModel(savedQuestion);

    }

    @Override
    @Cacheable("questions")
    public List<QuestionResponseModel> getQuestionsList(int page) {

        Pageable pageable = PageRequest.of(page, 40, Sort.by("date").descending());

        return questionRepository.findAll(pageable)
                .stream()
                .map(topic -> new QuestionResponseModel(topic))
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
