package com.camaat.first.controller;
import com.camaat.first.model.ImageResponseModel;
import com.camaat.first.repository.UserRepository;
import com.camaat.first.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {
    private final UserRepository userRepository;
    private final ImageService imageService;

    @Autowired
    public ImageController(UserRepository userRepository, ImageService imageService) {
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @PostMapping("/api/user/{username}/image")
    @PreAuthorize(value = "#username == authentication.name")
    public ResponseEntity setImage(@RequestParam("image") MultipartFile multipartFile,
                                   @PathVariable String username){

        ImageResponseModel responseModel= imageService.setImage(multipartFile);

         return ResponseEntity.ok(responseModel);
    }
}
