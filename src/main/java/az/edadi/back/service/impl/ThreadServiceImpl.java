package az.edadi.back.service.impl;

import az.edadi.back.entity.User;
import az.edadi.back.entity.message.Thread;
import az.edadi.back.entity.message.UserThread;
import az.edadi.back.exception.model.CreateDublicateThreadException;
import az.edadi.back.exception.model.UserNotFoundException;
import az.edadi.back.model.request.ThreadRequestModel;
import az.edadi.back.model.response.MessageResponseModel;
import az.edadi.back.model.response.ThreadResponseModel;
import az.edadi.back.repository.MessageRepository;
import az.edadi.back.repository.ThreadRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.repository.UserThreadRepository;
import az.edadi.back.service.ThreadService;
import az.edadi.back.utility.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;
    private final UserThreadRepository userThreadRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public ThreadResponseModel createThread(ThreadRequestModel threadRequestModel) {

        User targetUser = userRepository.findByUsername(threadRequestModel.getUsername()).orElseThrow(UserNotFoundException::new);
        if (targetUser.getId().equals(AuthUtil.getCurrentUserId()))
            throw new CreateDublicateThreadException();

        Optional<List<UserThread>> userThreadList = userThreadRepository.getThreadsBWithUserIds(targetUser.getId(), AuthUtil.getCurrentUserId());

        if (userThreadList.isPresent() && userThreadList.get().size() > 0)
            return new ThreadResponseModel(userThreadList.get().get(0).getThread());

        Thread thread = threadRepository.save(new Thread());
        UserThread userThread = new UserThread();
        UserThread userThread2 = new UserThread();

        userThread.setUser(new User(AuthUtil.getCurrentUserId()));
        userThread2.setUser(targetUser);

        userThread.setThread(thread);
        userThread2.setThread(thread);

        userThreadRepository.save(userThread);
        userThread2 = userThreadRepository.save(userThread2);
        thread.setUserThread(Arrays.asList(userThread2, userThread));

        return new ThreadResponseModel(threadRepository.findById(thread.getId()).get());

    }

    @Override
    public List<ThreadResponseModel> getThreads(int page) {
        Pageable pageable = PageRequest.of(page, 15);
        List<UserThread> threadList = userThreadRepository.findByUserId(AuthUtil.getCurrentUserId(), pageable);
        return threadList.stream()
                .map(userThread -> new ThreadResponseModel(userThread.getThread()))
                .collect(Collectors.toList());
    }

    @Override
    public ThreadResponseModel getThread(Long id) {
        Long currentUserId = AuthUtil.getCurrentUserId();
        UserThread userThread = userThreadRepository.findByUserIdAndThreadId(currentUserId, id).orElseThrow(
                () -> new AccessDeniedException("User cam't access this thread"));
        Thread thread = userThread.getThread();
        return addMessagesToThread(new ThreadResponseModel(thread));


    }

    ThreadResponseModel addMessagesToThread(ThreadResponseModel threadResponseModel) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("date").descending());
        List<MessageResponseModel> messageResponseModels = new ArrayList<>();
        messageRepository.findByThreadId(threadResponseModel.getThreadId(), pageable).stream().forEach(
                message -> messageResponseModels.add(new MessageResponseModel(message))
        );
        threadResponseModel.setMessages(messageResponseModels);
        return threadResponseModel;
    }
}
