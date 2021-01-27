package com.camaat.first.utility;

public class DataParser {
    public static Long objectToLong(Object var){
         if(var==null)
        return null;
        String varString = String.valueOf(var);
        Long varLong = Long.valueOf(varString);
        return varLong;
    }
}
