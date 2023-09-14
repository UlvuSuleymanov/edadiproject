package az.edadi.back.utility;

import java.util.Random;

public class UserUtil {
    public static String getRandomUsername(){
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz_1234567890";
        StringBuilder salt = new StringBuilder("user");
        Random rnd = new Random();
        while (salt.length() < 16) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}
