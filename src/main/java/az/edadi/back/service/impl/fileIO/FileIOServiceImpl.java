//package az.edadi.back.service.impl.fileIO;
//
//import az.edadi.back.service.FileIOService;
//import io.minio.errors.*;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//@Service
//@Profile("file-local")
//public class FileIOServiceImpl implements FileIOService {
//
//    @Override
//    public String saveFile(String key, MultipartFile multipartFile, String folder) {
//        return null;
//    }
//
//    @Override
//    public String saveFile(String key, File file, String folder) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
//        return null;
//    }
//
//    @Override
//    public String update(String key, File file) {
//        return key;
//    }
//
//    @Override
//    public void deleteFile(String key, String folder) {
//
//    }
//}
