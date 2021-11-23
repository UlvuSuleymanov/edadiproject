package az.edadi.back.model;

import az.edadi.back.constants.PhotoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageModel {
    private String urlS;
    private String url;

    public ImageModel(String imageName, PhotoEnum folder) {
        String rootUrl = PhotoEnum.ROOT_PHOTO_URL.getName()+folder.getName()+"/";
        urlS = rootUrl + PhotoEnum.IMAGE_SIZE_S.getName() + imageName;
        url = rootUrl + PhotoEnum.IMAGE_SIZE_L.getName() + imageName;
    }


}
