package az.edadi.back.service.impl.auth;

import az.edadi.back.service.LoginAttemptService;
import az.edadi.back.utility.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

    private static Map<String, Integer> attempts = new HashMap();
    private int MAX_LOGIN_ATTEMPT = 7;


    @Override
    public boolean isGoodAttemmpt() {
        String ip = AuthUtil.getcurrentIp();
        return !Optional.ofNullable(attempts.get(ip)).isPresent() || attempts.get(ip) < MAX_LOGIN_ATTEMPT;
    }

    @Override
    public void addAttempt() {
        String ip = AuthUtil.getcurrentIp();
        if (attempts.containsKey(ip))
            attempts.put(ip, attempts.get(ip) + 1);
        else
            attempts.put(ip, 0);
    }


    @Override
    public void clear() {
        String currentIp=AuthUtil.getcurrentIp();
        Optional.ofNullable(
                attempts.get(currentIp)).ifPresent(v -> attempts.remove(currentIp));
    }

    @Override
    public void clearAll() {
        attempts = new HashMap<>();
    }
}


