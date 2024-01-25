package az.edadi.back.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/file/upload")
public class FileController {
    @GetMapping
    String uploadFile(@RequestParam("file") MultipartFile file){

        System.out.println(file.getOriginalFilename());
        return file.getContentType();

    }
}
