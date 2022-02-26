package az.edadi.back.utility;

import az.edadi.back.constants.AppConstants;

public class PhotoUtil {

    public static String getUniversityPhoto(String name) {
        return AppConstants.DOMAIN + "/image/university/" + name;
    }

}
