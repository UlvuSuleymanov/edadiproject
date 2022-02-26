package az.edadi.back.scheduler;

import az.edadi.back.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LoginAttemptScheduler {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Scheduled(fixedRate = 600000)
    public void calistirGorev() {
        loginAttemptService.clearAll();
    }

}
