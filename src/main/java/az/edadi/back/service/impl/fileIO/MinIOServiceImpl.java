package az.edadi.back.service.impl.fileIO;

import az.edadi.back.config.MinioConfig;
import az.edadi.back.model.ReadyFile;
import az.edadi.back.model.response.UploadFileRes;
import az.edadi.back.service.FileIOService;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import io.minio.MinioClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class MinIOServiceImpl implements FileIOService {

    private final MinioClient minioClient;
    private final MinioConfig minioConfig;
    private final String bucket = "edadi";

    public MinIOServiceImpl(MinioClient minioClient, MinioConfig minioConfig) {
        this.minioClient = minioClient;
        this.minioConfig = minioConfig;
    }


    @Override
    public UploadFileRes uploadUserImage(MultipartFile multipartFile, File file, String key) {
        return null;
    }

    @Override
    public String saveFile(ReadyFile readyFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        return readyFile.getFileItem().toString();
    }


    @Override
    public String update(String key, File file) {
        return null;
    }

    @Override
    public void deleteFile(String key, String folder) {

    }


}
