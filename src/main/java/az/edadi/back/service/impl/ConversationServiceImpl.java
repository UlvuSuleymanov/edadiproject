package az.edadi.back.service.impl;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.message.Conversation;
import az.edadi.back.entity.message.Thread;
import az.edadi.back.exception.model.EdadiEntityNotFoundException;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.UserSummary;
import az.edadi.back.model.request.ConversationReq;
import az.edadi.back.model.response.ConversationRes;
import az.edadi.back.model.response.MessageResponseModel;
import az.edadi.back.repository.ConversationRepository;
import az.edadi.back.repository.ThreadRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.ConversationService;
import az.edadi.back.service.MessageService;
import az.edadi.back.utility.AuthUtil;
import az.edadi.back.utility.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final ThreadRepository threadRepository;
    private final MessageService messageService;


    @Override
    public ConversationRes getConversationByUsername(ConversationReq conversationReq) {
        Long currentUserId = AuthUtil.getCurrentUserId();
        User currentUser = userRepository.findById(currentUserId).orElseThrow(
                () -> new EdadiEntityNotFoundException(EntityType.USER)
        );
        User targetUser = userRepository.findByUsername(conversationReq.getUsername()).orElseThrow(
                () -> new EdadiEntityNotFoundException(EntityType.USER));

        if (targetUser.getId().equals(currentUserId))
            throw new UserAuthorizationException();

        Optional<Long> conversationId = conversationRepository.getConversation(
                Arrays.asList(currentUserId, targetUser.getId())
        );

        Conversation returnConversation = conversationId.map(aLong -> conversationRepository.findById(aLong).get())
                .orElseGet(() -> createConversation(Arrays.asList(currentUser, targetUser)));

        return getConversationById(returnConversation.getId());
    }

    @Override
    public ConversationRes getConversationById(Long id) {
        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new EdadiEntityNotFoundException(EntityType.CONVERSATION));
        threadRepository.findByUserIdAndConversationId(AuthUtil.getCurrentUserId(), conversation.getId())
                .orElseThrow(UserAuthorizationException::new);

        return toConversationRes(conversation);
    }

    private Conversation createConversation(List<User> userList) {
        Conversation conversation = new Conversation();
        List<Thread> conversationThreads = userList.stream()
                .map(user -> new Thread(conversation, user))
                .toList();
        conversation.setThreadList(conversationThreads);
        conversationRepository.saveAndFlush(conversation);
        return conversation;
    }

    @Override
    public List<ConversationRes> getConversationList(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("lastMessageDate").descending());
        return conversationRepository.getConversationList(AuthUtil.getCurrentUserId(), pageable)
                .stream()
                .map(this::toConversationRes)
                .toList();

    }

    @Async
    @Override
    public void updateConversationLastMessageDate(Long conversationId) {
        Conversation conversation=conversationRepository.findById(conversationId)
                .orElseThrow(()->  new EdadiEntityNotFoundException(EntityType.CONVERSATION));
         conversation.setLastMessageDate(new Date());
         conversationRepository.save(conversation);
    }

    private ConversationRes toConversationRes(Conversation conversation) {
        List<Thread> threadList = conversation.getThreadList();
        ConversationRes conversationRes = new ConversationRes();
        threadList.forEach(thread -> {
                    if(thread.getUser().getId().equals(AuthUtil.getCurrentUserId()))
                        conversationRes.setThreadId(thread.getId());
                    else
                        conversationRes.setTargetUser(new UserSummary(thread.getUser()));
                });
        conversationRes.setId(conversation.getId());
        conversationRes.setDate(DateUtil.getHowLongAgoString(conversation.getDateModified()));
        List<MessageResponseModel> messageResponseModelList = messageService.getMessages(conversation.getId(), 1);
        conversationRes.setMessageList(messageResponseModelList);
        return conversationRes;
    }
}
