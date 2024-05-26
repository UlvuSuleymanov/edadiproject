package az.edadi.back.controller;
import az.edadi.back.model.response.UploadFileRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/file/upload")
public class UploadFileController {

    @PostMapping
    ResponseEntity<UploadFileRes> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok( new UploadFileRes(file.getOriginalFilename(),file.getContentType()));
    }
}
