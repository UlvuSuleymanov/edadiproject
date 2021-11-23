package az.edadi.back.utility;
import az.edadi.back.constants.PhotoEnum;

public class PhotoUtil {


    public static String getFullPhotoUrl(String name)
    {
        return PhotoEnum.ROOT_PHOTO_URL.getName()+ name;
    }

    public static String getThumbPhotoUrl(String name)
    {
        return PhotoEnum.ROOT_PHOTO_URL.getName()+"thumb"+name;
    }

    public static String getUniversityPhoto(String name){ return PhotoEnum.DOMAIN.getName()+"/image/university/"+ name; }



}
