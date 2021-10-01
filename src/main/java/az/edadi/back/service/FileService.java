package az.edadi.back.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {
     String saveProfilePhoto(String key, File file, String folder);

     String update (String key);
     void deleteProfileImage(String key);
     File convertMultiPartToFile(MultipartFile multipartFile) throws IOException;

}
