package az.edadi.back.service.impl;

import az.edadi.back.constants.type.UploadingType;
import az.edadi.back.entity.app.FileItem;
import az.edadi.back.entity.auth.User;
import az.edadi.back.model.ReadyFile;
import az.edadi.back.model.response.SimpleFileRes;
import az.edadi.back.model.response.UploadFileRes;
import az.edadi.back.repository.FileItemRepository;
import az.edadi.back.service.FileIOService;
import az.edadi.back.service.FileService;
import az.edadi.back.utility.AuthUtil;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileIOService s3IOServiceImpl;
    private final FileItemRepository fileRepository;

    @Override
    public UploadFileRes uploadUserProfileImage(MultipartFile multipartFile)  {
        UUID uuid = UUID.randomUUID();
        File originalImage = convertMultiPartToFile(multipartFile,uuid);
        UploadFileRes uploadFileRes = s3IOServiceImpl.uploadUserImage(multipartFile, originalImage, uuid.toString());
        FileItem fileItem = getFileItem(multipartFile, UploadingType.USER);
        fileItem.setUrl(uploadFileRes.getUrl());
        fileItem.setUuid(uuid);
        fileRepository.saveAndFlush(fileItem);
        originalImage.delete();
        return uploadFileRes;
    }

    @Override
    public List<SimpleFileRes> uploadFile(final String parent,
                                          List<MultipartFile> multipartFiles) throws ExecutionException, InterruptedException, IOException {
        UploadingType uploadingType = UploadingType.of(parent);
        List<ReadyFile> files = new ArrayList<>();
        List<ReadyFile> uniques = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {

            ReadyFile readyFile = new ReadyFile(multipartFile, getFileItem(multipartFile, uploadingType), uploadingType);
            files.add(readyFile);
//            uniques.add(readyFile);

//            if (uploadingType.getSizes().contains("M")) {
//                readyFile = new ReadyFile(getImageSizeM(multipartFile), getCopy(readyFile.getFileItem(),getImageSizeM(multipartFile)), uploadingType);
//                files.add(readyFile);
//            }
//            if (uploadingType.getSizes().contains("S")) {
//                readyFile = new ReadyFile(getImageSizeS(multipartFile), getCopy(readyFile.getFileItem(),getImageSizeS(multipartFile)), uploadingType);
//                files.add(readyFile);
//            }

        }
        files = saveReadyFiles(files);

        return files.stream().map(readyFile -> new SimpleFileRes(readyFile.getFileItem())).toList();
    }

    public File convertMultiPartToFile(MultipartFile multipartFile, UUID uuid) {
        File convFile = new File(Objects.requireNonNull(uuid.toString()));

        try {

            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();


        } catch (Exception e) {
            return null;
        }

        return convFile;
    }

    private FileItem getFileItem(MultipartFile multipartFile, UploadingType uploadingType) {
        UUID uuid = UUID.randomUUID();
        FileItem fileItem = new FileItem();
        fileItem.setUuid(uuid);
        fileItem.setParent(uploadingType.getParent());
        fileItem.setFolder(uploadingType.getFolder());
        fileItem.setSize(multipartFile.getSize());
        fileItem.setOrginalName(multipartFile.getOriginalFilename());
        fileItem.setContentType(multipartFile.getContentType());
        fileItem.setUsed(false);
        fileItem.setUser(new User(AuthUtil.getCurrentUserId()));
        return fileItem;
    }



    void saveFile(ReadyFile readyFile) throws InterruptedException, ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        fileRepository.save(readyFile.getFileItem());
        s3IOServiceImpl.saveFile(readyFile);
    }


    List<ReadyFile> saveReadyFiles(List<ReadyFile> files) throws ExecutionException, InterruptedException {
        CompletableFuture<Void>[] fileFutures = new CompletableFuture[files.size()];

        for (int i = 0; i < files.size(); i++) {

            int index = i;
            fileFutures[i] = CompletableFuture.runAsync(() -> {
                try {
                    saveFile(files.get(index));
                } catch (InterruptedException | ServerException | InsufficientDataException | ErrorResponseException |
                         IOException | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException |
                         XmlParserException | InternalException e) {
                    throw new RuntimeException(e);
                }
            });

        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(fileFutures);
        CompletableFuture<Void> result = allOf.thenRun(() -> {
            System.out.println("All files processed successfully!");
        });
        result.get();
        return files;
    }


}
