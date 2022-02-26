package az.edadi.back.service.impl;

import az.edadi.back.constants.AppConstants;
import az.edadi.back.model.ImageModel;
import az.edadi.back.repository.ImageRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.FileService;
import az.edadi.back.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageServiceImpl implements ImageService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final FileService fileService;

    @Override
    public ImageModel saveProfilePhoto(String name, MultipartFile multipartFile) throws IOException {
        log.info("User upload profile image named {}", name);

        File orginalImage = convertMultiPartToFile(multipartFile);
        fileService.saveFile(AppConstants.IMAGE_SIZE_L + name, orginalImage, AppConstants.USER_IMAGE_FOLDER);

        //reduce the size of the picture
        File imageS = getSSizePhoto(orginalImage);
        fileService.saveFile(AppConstants.IMAGE_SIZE_S + name, imageS, AppConstants.USER_IMAGE_FOLDER);

        orginalImage.delete();
        imageS.delete();

        return new ImageModel(name, AppConstants.USER_IMAGE_FOLDER);
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
        fileService.deleteFile(AppConstants.IMAGE_SIZE_L + name, AppConstants.USER_IMAGE_FOLDER);
        fileService.deleteFile(AppConstants.IMAGE_SIZE_S + name, AppConstants.USER_IMAGE_FOLDER);
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
