package az.edadi.back.utility;


import az.edadi.back.constants.ImageEnum;

public class ImageUtil {


    public static String getPhotoUrl(String name)
    {
        return ImageEnum.ROOT_IMAGE_URL.getName()+name;
    }



}
