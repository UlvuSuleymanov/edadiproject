package az.edadi.back.model;

import az.edadi.back.constants.AppConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageModel {
    private String urlS;
    private String url;

    public ImageModel(String imageName, String folder) {
        String rootUrl = AppConstants.ROOT_IMAGE_URL+folder;
        urlS = rootUrl + AppConstants.IMAGE_SIZE_S + imageName;
        url = rootUrl + AppConstants.IMAGE_SIZE_L + imageName;
    }


}
