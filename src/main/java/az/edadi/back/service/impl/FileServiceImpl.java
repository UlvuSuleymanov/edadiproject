package az.edadi.back.service.impl;

import az.edadi.back.service.FileService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Profile("dev")
public class FileServiceImpl implements FileService {
    @Override
    public String saveFile(String key, File file, String folder) {
        return key;
    }

    @Override
    public String update(String key, File file) {
        return key;
    }

    @Override
    public void deleteFile(String key, String folder) {

    }
}
