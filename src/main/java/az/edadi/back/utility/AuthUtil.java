package az.edadi.back.utility;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
    public static  Long getCurrentUserId(){
        if(userIsAuthenticated())
         return DataParser.objectToLong(SecurityContextHolder.getContext().getAuthentication().getCredentials());
        return null;
    }

    public static String getCurrentUsername(){
       return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static  boolean userIsAuthenticated(){
      return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

}
