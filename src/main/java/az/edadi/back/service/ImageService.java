package az.edadi.back.service;

import az.edadi.back.model.ImageModel;
import az.edadi.back.model.request.ImageRequestModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageService {

//    String setImage(MultipartFile multipartFile, boolean  hasThumb);
    ImageModel saveImage(String name, MultipartFile multipartFile) throws IOException;

    File getMSizePhoto(File file) throws IOException;

    File getSSizePhoto(File file) throws IOException;

    void deleteFiles(String name);




}
