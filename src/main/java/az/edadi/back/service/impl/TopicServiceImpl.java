package az.edadi.back.service.impl;

import az.edadi.back.entity.Topic;
import az.edadi.back.entity.User;
import az.edadi.back.model.SummaryModel;
import az.edadi.back.repository.TopicRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.TopicService;
import az.edadi.back.utility.AuthUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TopicServiceImpl implements TopicService {

     private final TopicRepository topicRepository;
     private final UserRepository userRepository;

    public TopicServiceImpl(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }


    @Override
    public SummaryModel addTopic(String title) {
     User user = userRepository.getById(AuthUtil.getCurrentUserId());
     Topic topic = new Topic();
     topic.setTitle(title);
     topic.setDate(new Date());
     topic.setUser(user);
     Topic t= topicRepository.save(topic);
     return new SummaryModel(topic.getId(),topic.getTitle());
    }

    @Override
    public List<SummaryModel> getTopics() {
        return topicRepository.findAll()
                .stream()
                .map(topic -> new SummaryModel(topic.getId(),topic.getTitle()))
                .collect(Collectors.toList());
    }
}
