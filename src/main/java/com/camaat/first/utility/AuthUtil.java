package com.camaat.first.utility;

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
      boolean a= SecurityContextHolder.getContext().getAuthentication().getCredentials()!=null;
      return  a;
    }

}
