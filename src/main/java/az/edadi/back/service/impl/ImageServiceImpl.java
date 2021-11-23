package az.edadi.back.service.impl;

import az.edadi.back.constants.PhotoEnum;
import az.edadi.back.model.ImageModel;
import az.edadi.back.repository.ImageRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.FileService;
import az.edadi.back.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final FileService fileService;

    public ImageServiceImpl(
            UserRepository userRepository,
            ImageRepository imageRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.fileService = fileService;
    }


    @Override
    public ImageModel saveProfilePhoto(String name, MultipartFile multipartFile) throws IOException {
        log.info("User upload profile image named {}", name);

        File orginalImage = convertMultiPartToFile(multipartFile);
        fileService.saveFile(PhotoEnum.IMAGE_SIZE_L.getName() + name, orginalImage, PhotoEnum.USER_IMAGE_FOLDER);

        //reduce the size of the picture
        File imageS = getSSizePhoto(orginalImage);
        fileService.saveFile(PhotoEnum.IMAGE_SIZE_S.getName() + name, imageS, PhotoEnum.USER_IMAGE_FOLDER);

        orginalImage.delete();
        imageS.delete();

        return new ImageModel(name, PhotoEnum.USER_IMAGE_FOLDER);
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
    public void deleteUserOldImages(String name) {
        log.info("Old pictures are deleted...");
        fileService.deleteFile(PhotoEnum.IMAGE_SIZE_L.getName() + name, PhotoEnum.USER_IMAGE_FOLDER);
        fileService.deleteFile(PhotoEnum.IMAGE_SIZE_S.getName() + name, PhotoEnum.USER_IMAGE_FOLDER);
        log.info("Files are deleted");
    }

    @Override
    public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

}
