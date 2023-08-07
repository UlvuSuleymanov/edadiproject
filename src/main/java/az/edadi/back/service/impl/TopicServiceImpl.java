package az.edadi.back.service.impl;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.entity.Topic;
import az.edadi.back.entity.User;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.request.TopicRequestModel;
import az.edadi.back.model.response.TopicResponseModel;
import az.edadi.back.repository.TopicRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.TopicService;
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
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    @Override
    @CacheEvict(cacheNames = "topics", allEntries = true)
    public TopicResponseModel addTopic(TopicRequestModel topicRequestModel) {

        User user = userRepository.getById(AuthUtil.getCurrentUserId());
        Topic topic = new Topic();
        topic.setTitle(topicRequestModel.getTitle());
        topic.setDate(new Date());
        topic.setUser(user);
        Topic t = topicRepository.save(topic);
        return new TopicResponseModel(t);

    }

    @Override
    @Cacheable("topics")
    public List<TopicResponseModel> getTopicList(int page) {

        Pageable pageable = PageRequest.of(page, 40, Sort.by("date").descending());

        return topicRepository.findAll(pageable)
                .stream()
                .map(topic -> new TopicResponseModel(topic))
                .collect(Collectors.toList());
    }

    @Override
    public TopicResponseModel getTopic(String slug) {
        Long id = SlugUtil.getId(slug);
        Topic topic = topicRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No Topic found with the id " + id)
        );
        return new TopicResponseModel(topic);
    }

    @Override
    @CacheEvict(cacheNames = "topics", allEntries = true)
    public void deleteTopic(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        if (!topic.getUser().getId().equals(AuthUtil.getCurrentUserId()) &&
                !AuthUtil.hasAuthority(UserAuthority.ADMIN_UPDATE))
            throw new UserAuthorizationException();

        topicRepository.delete(topic);
        log.info("Topic with id {} was deleted", id);

    }
}
