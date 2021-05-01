package com.camaat.first.utility;

import java.util.regex.Pattern;

public class PatternFactory {

    public static Pattern getUsernamePattern(){

        String regex = "^[a-z0-9_-]{3,16}$";

        return   Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }
}
