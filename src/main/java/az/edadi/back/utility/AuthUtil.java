package az.edadi.back.utility;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
    public static  Long getCurrentUserId(){

        if(userIsAuthenticated())
        {
         return DataParser.objectToLong(SecurityContextHolder.getContext().getAuthentication().getCredentials());
        }

        return null;
    }

    public static  boolean userIsAuthenticated(){
      return SecurityContextHolder.getContext().getAuthentication().getCredentials()!=null;
    }

}
