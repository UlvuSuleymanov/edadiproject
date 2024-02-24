package az.edadi.back.service.impl;

import az.edadi.back.constants.type.UploadingType;
import az.edadi.back.entity.app.FileItem;
import az.edadi.back.entity.auth.User;
import az.edadi.back.model.ReadyFile;
import az.edadi.back.model.response.SimpleFileRes;
import az.edadi.back.repository.FileItemRepository;
import az.edadi.back.service.FileIOService;
import az.edadi.back.service.FileService;
import az.edadi.back.utility.AuthUtil;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class FileServiceImpl implements FileService {
    private final FileIOService fileIOService;
    private final FileItemRepository fileRepository;

    public FileServiceImpl(FileIOService fileIOService, FileItemRepository fileRepository) {
        this.fileIOService = fileIOService;
        this.fileRepository = fileRepository;
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

    public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

    private FileItem getFileItem(MultipartFile multipartFile, UploadingType uploadingType) {
        UUID uuid = UUID.randomUUID();
        String extension = "." + multipartFile.getContentType().split("/")[1];
        FileItem fileItem = new FileItem();
        fileItem.setId(uuid);
        fileItem.setName(uploadingType.getFolder() + uuid);
        fileItem.setDate(new Date());
        fileItem.setParent(uploadingType.getParent());
        fileItem.setFolder(uploadingType.getFolder());
        fileItem.setSize(multipartFile.getSize());
        fileItem.setOrginalName(multipartFile.getOriginalFilename());
        fileItem.setContentType(multipartFile.getContentType());
        fileItem.setExtension(extension);
        fileItem.setUsed(false);
        fileItem.setUser(new User(AuthUtil.getCurrentUserId()));
        return fileItem;
    }

    private ReadyFile getThumbReadyFile(ReadyFile readyFile,MultipartFile newMultipart, String size){
        FileItem fileItem1 = readyFile.getFileItem();
        fileItem1.setName(fileItem1.getName()+size);
        readyFile.setMultipartFile(newMultipart);
        return readyFile;
    }

    void saveFile(ReadyFile readyFile) throws InterruptedException, ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        fileRepository.save(readyFile.getFileItem());
        fileIOService.saveFile(readyFile);
//there are a lot of bugs
//        if(readyFile.getUploadingType().getSizes().contains("M")){
//            fileIOService.saveFile(getThumbReadyFile(readyFile,getImageSizeM(readyFile.getMultipartFile()),"M"));
//        }
//        if(readyFile.getUploadingType().getSizes().contains("S")){
//            fileIOService.saveFile(getThumbReadyFile(readyFile,getImageSizeS(readyFile.getMultipartFile()),"S"));
//        }

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


//    public MultipartFile getImageSizeM(MultipartFile file) throws IOException {
//        File temp = new File(UUID.randomUUID().toString());
//        temp.createNewFile();
//        Thumbnails.of(file.getInputStream())
//                .size(160, 160)
//                .toFile(temp);
//        InputStream inputStream = new FileInputStream(temp);
//        temp.delete();
//        return new MockMultipartFile("thumb", inputStream);
//    }
//
//    public MultipartFile getImageSizeS(MultipartFile file) throws IOException {
//        File temp = new File(UUID.randomUUID().toString());
//        temp.createNewFile();
//        Thumbnails.of(file.getInputStream())
//                .size(100, 100)
//                .toFile(temp);
//        InputStream inputStream = new FileInputStream(temp);
//        temp.delete();
//        return new MockMultipartFile("thumb", inputStream);
//    }


}
