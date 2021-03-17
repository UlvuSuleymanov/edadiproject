package com.camaat.first.utility;

public class DataParser {
    public static Long objectToLong(Object var){

        if(var!=null && !var.toString().equals(""))
        {
            return  Long.valueOf(var.toString());
        }
        return null;

    }
}
