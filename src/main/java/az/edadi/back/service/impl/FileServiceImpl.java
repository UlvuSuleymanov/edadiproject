package az.edadi.back.service.impl;

import az.edadi.back.constants.FileType;
import az.edadi.back.entity.app.FileItem;
import az.edadi.back.entity.auth.User;
import az.edadi.back.repository.FileItemRepository;
 import az.edadi.back.service.FileIOService;
import az.edadi.back.service.FileService;
import az.edadi.back.utility.AuthUtil;
import io.minio.errors.*;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {
    private final FileIOService fileIOService;
    private final FileItemRepository fileRepository;

    public FileServiceImpl(FileIOService fileIOService, FileItemRepository fileRepository) {
        this.fileIOService = fileIOService;
        this.fileRepository = fileRepository;
    }


    @Override
    public List<String> uploadFile(final String parent, List<MultipartFile> multipartFile) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String name = UUID.randomUUID().toString();
        String root="http://localhost:9000/edadi/";
        FileType fileType = FileType.of(parent);
        List<FileItem> files = save(multipartFile,fileType);

        return files.stream().map(fileItem -> { return root+fileItem.getFileName();}).toList();
    }


    List<FileItem> save(List<MultipartFile> files, FileType parent) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<FileItem> fileItems = new ArrayList<>();
        System.out.println(files.size());
         for (MultipartFile multipartFile:files  ) {
             String name = UUID.randomUUID().toString();

             File file =convertMultiPartToFile(multipartFile);
            fileIOService.saveFile(name,multipartFile,parent.getFolder());

            FileItem fileItem = new FileItem();
            fileItem.setFileName(name+"."+multipartFile.getContentType().split("/")[1]);
            fileItem.setType(parent.getParent());
            fileItem.setUsed(false);
            fileItem.setUser(new User(AuthUtil.getCurrentUserId()));
            fileItem.setDate(new Date());
            fileItem.setFolder(parent.getFolder());
            fileRepository.save(fileItem);
            fileItems.add(fileItem);
        }

        return fileItems;
    }



    public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }


}
