package az.edadi.back.service;

import az.edadi.back.model.ImageModel;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface ImageService {

    String saveProfilePhoto(String name, MultipartFile multipartFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    File getSSizePhoto(File file) throws IOException;

    void deleteUserOldImages(String name);

    File convertMultiPartToFile(MultipartFile multipartFile) throws IOException;

}
