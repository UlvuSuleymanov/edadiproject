package com.camaat.first.utility;


import com.camaat.first.constants.ImageEnum;

public class ImageUtil {


    public static String getPhotoUrl(String name)
    {
        return ImageEnum.ROOT_IMAGE_URL.getName()+name;
    }



}
