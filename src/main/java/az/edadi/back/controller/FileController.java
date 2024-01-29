package az.edadi.back.controller;

import az.edadi.back.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

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
            @RequestParam("file") MultipartFile[] files) throws InterruptedException {

        Thread.sleep(3000);
        System.out.println();
        System.out.println(files.length);
        return Arrays.asList("assets/images/roommate-default.png","assets/images/roommate-default.png");

    }
}
