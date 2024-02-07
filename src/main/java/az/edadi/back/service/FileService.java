package az.edadi.back.service;

import az.edadi.back.model.response.SimpleFileRes;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FileService {

    List<SimpleFileRes> uploadFile(String parent, List<MultipartFile> multipartFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, ExecutionException, InterruptedException;
}
