package com.camaat.first.service.impl;

import com.camaat.first.service.ImageService;
import com.camaat.first.utility.UserEnum;
import com.camaat.first.service.S3Service;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    S3Service s3Service;

    @Value("${aws.photoUrl}")
    private static String photoUrl;

    @Override
    @Transactional
    public String   setImage(MultipartFile  multipartFile, String name,Long id ) {
        File file= null;
        try {
            file = convertMultiPartToFile(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File smallImage=getSmallPicture(file);

        if(name.equals(UserEnum.DEFAULT_USER_IMAGE_NAME.getImageName()))
        {   name="image"+id;
            s3Service.setPhoto(name,smallImage);

        }

        else {
            s3Service.updatePhoto(name,smallImage);
         }


        file.delete();
        smallImage.delete();


        return name;
    }

    @Override
    public File getSmallPicture(File file) {

        try {
                     Thumbnails.of(file)
                       .size(160,160)
                       .toFile(file);
                     return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

    public static String getPhotoUrl(String name) {
        return photoUrl+name;
     }


}
