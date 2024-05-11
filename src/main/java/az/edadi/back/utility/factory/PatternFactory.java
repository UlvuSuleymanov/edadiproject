package az.edadi.back.utility.factory;

import java.util.regex.Pattern;

public class PatternFactory {

    public static Pattern getUsernamePattern(){

        String regex = "[a-z0-9_]{1,16}";
        return   Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }
}
