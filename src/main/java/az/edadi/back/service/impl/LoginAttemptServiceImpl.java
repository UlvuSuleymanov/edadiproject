package az.edadi.back.service.impl;

import az.edadi.back.service.LoginAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

    private static Map<String, Integer> attempts = new HashMap();
    private int MAX_LOGIN_ATTEMPT = 7;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public boolean isGoodAttemmpt() {
        String ip = getIp();
        return !Optional.ofNullable(attempts.get(ip)).isPresent() || attempts.get(ip) < MAX_LOGIN_ATTEMPT;
    }

    @Override
    public void addAttempt() {
        String ip = getIp();
        if (attempts.containsKey(ip))
            attempts.put(ip, attempts.get(ip) + 1);
        else
            attempts.put(ip, 0);
    }

    String getIp() {
        Optional<String> ipHeader = Optional.ofNullable(httpServletRequest.getHeader("X-FORWARDED-FOR"));
        String ip = "anonymous";
        if (ipHeader.isPresent())
            ip = ipHeader.get().split(",")[0];
        return ip;
    }

    @Override
    public void clear() {
        Optional.ofNullable(attempts.get(getIp())).ifPresent(v -> attempts.remove(getIp()));
    }

    @Override
    public void clearAll() {
        attempts = new HashMap<>();
    }
}


