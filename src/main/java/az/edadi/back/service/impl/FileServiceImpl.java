package az.edadi.back.service.impl;

import az.edadi.back.constants.FileType;
import az.edadi.back.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(final String parent, MultipartFile multipartFile) throws IOException {
        String name = UUID.randomUUID().toString();
        FileType fileType = FileType.of(parent);
        File file = convertMultiPartToFile(multipartFile);

        return null;
    }

    public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }



}
