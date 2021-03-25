package com.camaat.first.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageService {

    String setImage(MultipartFile multipartFile, boolean  hasThumb);


    File getSmallPicture(File file);


    File convertMultiPartToFile(MultipartFile multipartFile) throws IOException;


}
