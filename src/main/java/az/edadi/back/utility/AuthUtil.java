package az.edadi.back.utility;

import az.edadi.back.constants.UserAuthority;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

public class AuthUtil {
    public static Long getCurrentUserId() {
        if (userIsAuthenticated())
            return DataParser.objectToLong(SecurityContextHolder.getContext().getAuthentication().getCredentials());
        return null;
    }

    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static boolean userIsAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public static String getcurrentIp(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        Optional<String> ipHeader = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR"));
        String ip = "anonymous";
        if (ipHeader.isPresent())
            ip = ipHeader.get().split(",")[0];
        return ip;
    }


    public static boolean hasAuthority(UserAuthority userAuthority) {

        if (!userIsAuthenticated())
            return false;
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasAuthority = false;
        for (SimpleGrantedAuthority simpleGrantedAuthority : simpleGrantedAuthorities) {
            hasAuthority = simpleGrantedAuthority.getAuthority().equals(userAuthority.getPermission());
            if (hasAuthority)
                break;
        }
        return hasAuthority;

    }

}
