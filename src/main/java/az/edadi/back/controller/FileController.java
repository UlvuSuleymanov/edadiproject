package az.edadi.back.controller;

import az.edadi.back.service.FileService;
import az.edadi.back.service.impl.FileServiceImpl;
import io.minio.errors.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/file/upload")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("{parent}")
    List<String> uploadFile(
            @PathVariable String parent,
            @RequestParam("file") List<MultipartFile> files) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        return fileService.uploadFile(parent,files);

    }
}
