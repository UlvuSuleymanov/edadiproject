package az.edadi.back.service;

import az.edadi.back.model.ImageModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageService {

    String saveProfilePhoto(String name, MultipartFile multipartFile) throws IOException;

    File getSSizePhoto(File file) throws IOException;

    void deleteUserOldImages(String name);

    File convertMultiPartToFile(MultipartFile multipartFile) throws IOException;

}
