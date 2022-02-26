package az.edadi.back.service.impl;

import az.edadi.back.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {
    private static Map<String, Integer> attempts = new HashMap();
    private int MAX_LOGIN_ATTEMPT = 7;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public boolean isGoodAttemmpt() {
        return (!Optional.ofNullable(attempts.get(httpServletRequest.getRemoteAddr())).isPresent()) ||
                attempts.get(httpServletRequest.getRemoteAddr()) < MAX_LOGIN_ATTEMPT;
    }

    @Override
    public void addAttempt() {
        String ip = Optional.ofNullable(httpServletRequest.getHeader("X-FORWARDED-FOR")).orElse(httpServletRequest.getRemoteAddr());

        if (attempts.containsKey(ip))
            attempts.put(ip, attempts.get(ip) + 1);
        else
            attempts.put(ip, 0);

    }

    @Override
    public void clear() {
        Optional.ofNullable(attempts.get(httpServletRequest.getRemoteAddr())).ifPresent(v -> attempts.remove(httpServletRequest.getRemoteAddr()));
    }

    @Override
    public void clearAll() {
        attempts = new HashMap<>();
    }
}


