package com.camaat.first.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageService {

    /**
     * This method get MultiPartFile and convert to File
     * Then get   current name of user's profile picture
     * if the picture is defauld picture method call setPhoto method
     * If the user already has a profile picture method call updatePhoto method
     * @param multipartFile picture sent by the user
     * @param name name of picture
     * @param id user's id
     * @return
     */
    String setImage(MultipartFile multipartFile,String name,Long id);

    /**
     * This method resize picture by thumbnailator
     * @param file
     * @return
     */
    File getSmallPicture(File file);

    //good method :)
    File convertMultiPartToFile(MultipartFile multipartFile) throws IOException;

    /**
     *
     * This method generate a photo url  using another method. I don't know why :|
     * @param name
     * @return
     */
//    String getPhotoUrl(String name);

}
