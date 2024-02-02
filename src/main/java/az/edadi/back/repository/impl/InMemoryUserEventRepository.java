package az.edadi.back.repository.impl;

import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.exception.model.UserEventLimitException;
import az.edadi.back.repository.UserEventsRepository;
import az.edadi.back.utility.AuthUtil;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserEventRepository implements UserEventsRepository {

    Map<Long, List<UserEvent>> events = new HashMap<>();

    @Override
    public void saveEvent(UserEvent userEvent) {

        Long id = AuthUtil.getCurrentUserId();
        List<UserEvent> userEventList = Optional.ofNullable(events.get(id))
                .orElse(new ArrayList<>());
        userEventList.add(userEvent);
        events.put(id, userEventList);
     }

    @Override
    public void check(final UserEvent userEvent) {
        saveEvent(userEvent);
        Long id = AuthUtil.getCurrentUserId();
        List<UserEvent> userEventList = Optional.ofNullable(events.get(id))
                .orElse(new ArrayList<>()).stream()
                .filter(event -> event.equals(userEvent))
                .toList();
        if (userEventList.size() > userEvent.getLimit())
            throw new UserEventLimitException(userEvent);
    }

    @Override
    public void clearAll() {
        events = new HashMap<>();
    }
}
