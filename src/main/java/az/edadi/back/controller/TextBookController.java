package az.edadi.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TextBookController {
    @PostMapping("/api/subject/{subjectId}/textbook")
    ResponseEntity addTextBook(@RequestBody MultipartFile file, @PathVariable Long subjectId)
    {

        return new ResponseEntity(HttpStatus.OK);
    }
}
