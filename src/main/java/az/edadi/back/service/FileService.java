package az.edadi.back.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {
     String save(String key, File file);
     String update (String key);
     File convertMultiPartToFile(MultipartFile multipartFile) throws IOException;

}
