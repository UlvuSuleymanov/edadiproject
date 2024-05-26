package az.edadi.back.controller;

import az.edadi.back.model.response.SimpleFileRes;
import az.edadi.back.model.response.UploadFileRes;
import az.edadi.back.service.FileService;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/upload/image")
public class UploadImageController {

    private final FileService fileService;
    @PostMapping("user")
    ResponseEntity<UploadFileRes> uploadFile(@RequestParam("file") MultipartFile file) {
        UploadFileRes fileRes= fileService.uploadUserProfileImage(file);
        return ResponseEntity.ok(fileRes);
    }

    @PostMapping("{parent}")
    List<SimpleFileRes> uploadFile(
            @PathVariable String parent,
            @RequestParam("file") List<MultipartFile> files) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, ExecutionException, InterruptedException {
        List<SimpleFileRes> fileRes= fileService.uploadFile(parent,files);

        return fileRes;
    }


}
