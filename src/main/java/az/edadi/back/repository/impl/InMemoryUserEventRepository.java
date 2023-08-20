package az.edadi.back.repository.impl;

import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.exception.model.UserEventLimitException;
import az.edadi.back.model.UserEventModel;
import az.edadi.back.repository.UserEventsRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserEventRepository implements UserEventsRepository {

    Map<Long, List<UserEvent>> events = new HashMap<>();

    @Override
    public UserEventModel saveEvent(UserEventModel userEventModel) {

        if (!events.containsKey(userEventModel.getUserId())) {
            events.put(userEventModel.getUserId(),new ArrayList<>());
        }
        events.get(userEventModel.getUserId()).add(userEventModel.getUserEvent());
        return userEventModel;
    }

    @Override
    public void check(UserEventModel userEventModel) {
        if (!events.containsKey(userEventModel.getUserId()))
            return;
        UserEvent currentEvent = userEventModel.getUserEvent();
        List<UserEvent> currentEventList = events.get(userEventModel.getUserId()).stream()
                .filter(event -> event.equals(currentEvent))
                .collect(Collectors.toList());

        if (currentEventList.size() > currentEvent.getLimit())
            throw new UserEventLimitException(userEventModel.getUserEvent());
    }

    @Override
    public void clearAll() {
        events = new HashMap<>();
    }
}
