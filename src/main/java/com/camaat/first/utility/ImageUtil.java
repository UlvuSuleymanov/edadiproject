package com.camaat.first.utility;


public class ImageUtil {


    public static String generatePhotoUrl(String name)
    {
        return ImageEnum.ROOT_IMAGE_URL.getName()+name;
    }
}
