package az.edadi.back.service.impl.fileIO;

import az.edadi.back.config.MinioConfig;
import az.edadi.back.service.FileIOService;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.minio.MinioClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinIOServiceImpl implements FileIOService{

    private final MinioClient minioClient;
    private final MinioConfig minioConfig;

    public MinIOServiceImpl(MinioClient minioClient, MinioConfig minioConfig) {
        this.minioClient = minioClient;
        this.minioConfig = minioConfig;
    }

     public String saveFile(String key, MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        InputStream inputStream = file.getInputStream();

        minioClient.putObject(PutObjectArgs.builder()
                .bucket("edadi")
                .object(key)
                .contentType(file.getContentType())
                .stream(inputStream, inputStream.available(), -1)
                .build());

        return key;
    }

    @Override
    public String saveFile(String key, File file, String folder) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        InputStream inputStream = new FileInputStream(file);

        minioClient.putObject(PutObjectArgs.builder()
                .bucket("edadi")
                .object(key+".jpeg")
                .stream(inputStream, file.length(), -1)
                .build());

        return key;
    }

    @Override
    public String update(String key, File file) {
        return null;
    }

    @Override
    public void deleteFile(String key, String folder) {

    }


}
