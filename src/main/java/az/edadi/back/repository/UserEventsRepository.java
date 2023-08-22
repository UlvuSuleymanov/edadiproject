package az.edadi.back.repository;

import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.model.UserEventModel;

public interface UserEventsRepository {
    void saveEvent(UserEvent userEvent);

    void check(UserEvent userEvent);

    void clearAll();
}
