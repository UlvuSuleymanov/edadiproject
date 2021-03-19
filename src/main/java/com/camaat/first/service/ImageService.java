package com.camaat.first.service;

import com.camaat.first.model.ImageResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageService {

    ImageResponseModel setImage(MultipartFile multipartFile);


    File getSmallPicture(File file);


    File convertMultiPartToFile(MultipartFile multipartFile) throws IOException;


}
