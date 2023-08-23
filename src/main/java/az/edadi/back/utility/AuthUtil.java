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


    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public static String getcurrentIp() {

        if (RequestContextHolder.getRequestAttributes() == null) {
            return "0.0.0.0";
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        for (String header: IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(header);
            if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
                String ip = ipList.split(",")[0];
                return ip;
            }
        }

        return request.getRemoteAddr();
    }
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
    @Deprecated
    public static String getcurrentIpAddress(){
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
