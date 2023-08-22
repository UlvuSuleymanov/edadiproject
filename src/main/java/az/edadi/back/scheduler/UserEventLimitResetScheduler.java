package az.edadi.back.scheduler;

import az.edadi.back.repository.UserEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserEventLimitResetScheduler {
    @Autowired
    private UserEventsRepository userEventsRepository;

    @Scheduled(fixedRate = 86400000)
    public void clearUserEvents() {
        userEventsRepository.clearAll();
    }

}
