package az.edadi.back.model;

import az.edadi.back.constants.PhotoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageModel {
    private String urlS;
    private String urlM;
    private String urlL;

    public ImageModel(String imageName, String type) {
        String rootUrl = PhotoEnum.ROOT_PHOTO_URL.getName();

        switch (type) {
            case "user":
                rootUrl = rootUrl + PhotoEnum.USER_IMAGE_FOLDER.getName()+"/";
                break;
            case "article":
                rootUrl = rootUrl + PhotoEnum.BLOG_IMAGE_FOLDER.getName()+"/";
                break;
        }


        urlS = rootUrl + PhotoEnum.IMAGE_SIZE_S.getName() + imageName;
        urlM = rootUrl + PhotoEnum.IMAGE_SIZE_M.getName() + imageName;
        urlL = rootUrl + PhotoEnum.IMAGE_SIZE_L.getName() + imageName;
    }


}
