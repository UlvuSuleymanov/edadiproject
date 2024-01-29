package az.edadi.back.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(String parent, MultipartFile multipartFile) throws IOException;
}
