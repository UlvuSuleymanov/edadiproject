package az.edadi.back.utility;

import az.edadi.back.constants.UserAuthority;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

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
