package az.edadi.back.security.listener;

import az.edadi.back.security.listener.event.LoginEvent;
import az.edadi.back.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @EventListener
    public void onApplicationEvent(LoginEvent loginEvent) {

        if (loginEvent.isSuccessfully())
            loginAttemptService.clear();
        else
            loginAttemptService.addAttempt();

    }
}
