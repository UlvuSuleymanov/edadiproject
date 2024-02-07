package az.edadi.back.service.impl;

import az.edadi.back.constants.FileType;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
                                          List<MultipartFile> multipartFiles) throws ExecutionException, InterruptedException {
        FileType fileType = FileType.of(parent);
        List<ReadyFile> readyFiles = multipartFiles
                .stream()
                .map(multipartFile -> {
                    return new ReadyFile(multipartFile, getFileItem(multipartFile, fileType));
                })
                .toList();
        readyFiles = saveReadyFiles(readyFiles);
        return readyFiles.stream().map(readyFile -> new SimpleFileRes(readyFile.getFileItem())).toList();
    }

    public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

    private FileItem getFileItem(MultipartFile multipartFile, FileType fileType) {
        final UUID uuid = UUID.randomUUID();
        String extension = "." + multipartFile.getContentType().split("/")[1];
        FileItem fileItem = new FileItem();
        fileItem.setId(uuid);
        fileItem.setName(fileType.getFolder()+uuid + extension);
        fileItem.setDate(new Date());
        fileItem.setParent(fileType.getParent());
        fileItem.setFolder(fileType.getFolder());
        fileItem.setSize(multipartFile.getSize());
        fileItem.setOrginalName(multipartFile.getOriginalFilename());
        fileItem.setContentType(multipartFile.getContentType());
        fileItem.setExtension(extension);
        fileItem.setUsed(false);
        fileItem.setUser(new User(AuthUtil.getCurrentUserId()));
        return fileItem;
    }

    void saveFile(ReadyFile readyFile) throws InterruptedException, ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        fileRepository.save(readyFile.getFileItem());
        fileIOService.saveFile(
                readyFile.getFileItem().getName(),
                readyFile.getMultipartFile());
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
