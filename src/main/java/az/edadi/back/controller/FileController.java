package az.edadi.back.controller;

import az.edadi.back.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
            @RequestParam("file") List<MultipartFile> files) throws InterruptedException {

        return files.stream().map(multipartFile -> UUID.randomUUID().toString()).toList();

    }
}
