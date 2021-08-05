package az.edadi.back.service.impl;

import az.edadi.back.service.ImageService;
import az.edadi.back.service.S3Service;
import az.edadi.back.repository.ImageRepository;
import az.edadi.back.repository.UserRepository;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {


    private final UserRepository userRepository;
    private final ImageRepository imageRepository;


    private static String photoUrl;




    @Autowired
    public ImageServiceImpl(
                            UserRepository userRepository,
                            ImageRepository imageRepository) {

        this.userRepository = userRepository;
        this.imageRepository = imageRepository;

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









}
