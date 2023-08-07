package az.edadi.back.config;

import az.edadi.back.constants.AppConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FileConfig {

    @Value("${app.constants.domain}")
    private String domain;
    @Value("${app.constants.image-url}")
    private String imageUrl;
    @Value("${app.constants.user.default-image}")
    private String userDefaultImageUrl;
    @Value("${app.constants.user.image-folder}")
    private String userImageFolder;
    @Value("${app.constants.blog.image-folder}")
    private String blogImageFolder;
    @Value("${app.constants.blog.default-image}")
    private String blogDefaultImage;
    @Value("${app.constants.file.folder}")
    private String fileFolder;
    @Value("${app.constants.image.size-s}")
    private String imageSizeS;


    @PostConstruct
    public void postConstruct() {
        AppConstants.DOMAIN=domain;
        AppConstants.ROOT_IMAGE_URL=imageUrl;
        AppConstants.USER_DEFAULT_PHOTO=userDefaultImageUrl;
        AppConstants.USER_IMAGE_FOLDER=userImageFolder;
        AppConstants.BLOG_IMAGE_FOLDER=blogImageFolder;
        AppConstants.DOCUMENT_FILES_FOLDER=fileFolder;
        AppConstants.IMAGE_SIZE_L="";
        AppConstants.IMAGE_SIZE_S=imageSizeS;

    };
}
