package az.edadi.back.service;

import az.edadi.back.model.ReadyFile;
import az.edadi.back.model.response.UploadFileRes;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface FileIOService {


    UploadFileRes uploadUserImage(MultipartFile multipartFile, File file, String key);

    String saveFile(ReadyFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    String update(String key, File file);

    void deleteFile(String key, String folder);
}
