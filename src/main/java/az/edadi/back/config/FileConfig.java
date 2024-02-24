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


    @PostConstruct
    public void postConstruct() {
        AppConstants.DOMAIN=domain;
        AppConstants.ROOT_IMAGE_URL=imageUrl;
    };
}
