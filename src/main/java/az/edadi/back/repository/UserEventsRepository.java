package az.edadi.back.repository;

import az.edadi.back.model.UserEventModel;

public interface UserEventsRepository {
    UserEventModel saveEvent(UserEventModel userEventModel);

    void check(UserEventModel userEventModel);

    void clearAll();
}
