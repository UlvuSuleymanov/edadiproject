package az.edadi.back.service;

import az.edadi.back.model.response.UploadFileRes;
import org.springframework.web.multipart.MultipartFile;

public interface UploadImageService {
    UploadFileRes uploadFile(MultipartFile multipartFile);
}
