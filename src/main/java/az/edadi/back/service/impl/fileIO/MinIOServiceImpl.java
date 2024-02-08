package az.edadi.back.service.impl.fileIO;

import az.edadi.back.config.MinioConfig;
import az.edadi.back.model.ReadyFile;
import az.edadi.back.service.FileIOService;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import io.minio.MinioClient;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinIOServiceImpl implements FileIOService {

    private final MinioClient minioClient;
    private final MinioConfig minioConfig;
    private final String bucket = "edadi";

    public MinIOServiceImpl(MinioClient minioClient, MinioConfig minioConfig) {
        this.minioClient = minioClient;
        this.minioConfig = minioConfig;
    }

    @Override
    public String saveFile(ReadyFile readyFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        InputStream inputStream = readyFile.getMultipartFile().getInputStream();
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(readyFile.getFileItem().getName())
                .contentType(readyFile.getFileItem().getContentType())
                .stream(inputStream, inputStream.available(), -1)
                .build());
        return readyFile.getFileItem().getName();
    }


    @Override
    public String update(String key, File file) {
        return null;
    }

    @Override
    public void deleteFile(String key, String folder) {

    }


}
