package az.edadi.back.service.impl;

import az.edadi.back.constants.PhotoEnum;
import az.edadi.back.model.ImageModel;
import az.edadi.back.repository.ImageRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.FileService;
import az.edadi.back.service.ImageService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {


    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final FileService fileService;

    @Autowired
    public ImageServiceImpl(
            UserRepository userRepository,
            ImageRepository imageRepository, FileService fileService) {

        this.userRepository = userRepository;
        this.imageRepository = imageRepository;

        this.fileService = fileService;
    }


    @Override
    public ImageModel saveImage(String name, MultipartFile multipartFile) throws IOException {

        File orginalImage = fileService.convertMultiPartToFile(multipartFile);
        fileService.saveProfilePhoto(PhotoEnum.IMAGE_SIZE_L.getName()+name,orginalImage,PhotoEnum.USER_IMAGE_FOLDER.getName());

        File imageS = getSSizePhoto(orginalImage);
        fileService.saveProfilePhoto( PhotoEnum.IMAGE_SIZE_S.getName()+name, imageS,PhotoEnum.USER_IMAGE_FOLDER.getName());


        File imageM = getMSizePhoto(orginalImage);
        fileService.saveProfilePhoto(PhotoEnum.IMAGE_SIZE_M.getName()+name, imageM,PhotoEnum.USER_IMAGE_FOLDER.getName());

        orginalImage.delete();
        imageS.delete();
        imageM.delete();

        return new ImageModel(name,"user");
    }

    @Override
    public File getMSizePhoto(File file) throws IOException {
        Thumbnails.of(file)
                .size(300, 300)
                .toFile(file);
        return file;
    }

    @Override
    public File getSSizePhoto(File file) throws IOException {
        Thumbnails.of(file)
                .size(160, 160)
                .toFile(file);
        return file;
    }

    @Override
    @Async("deleteFileExecutor")
    public void deleteFiles(String name) {
        fileService.deleteProfileImage(PhotoEnum.IMAGE_SIZE_L.getName() + name);
        fileService.deleteProfileImage(PhotoEnum.IMAGE_SIZE_M.getName() + name);
        fileService.deleteProfileImage(PhotoEnum.IMAGE_SIZE_S.getName() + name);
    }


}
