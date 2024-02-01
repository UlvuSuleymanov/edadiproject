package az.edadi.back.service.impl.fileIO;

import az.edadi.back.service.FileIOService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
@Profile("file-minio")

public class MinIOServiceImpl implements FileIOService {
    @Override
    public String saveFile(String key, File file, String folder) {
        return null;
    }

    @Override
    public String update(String key, File file) {
        return null;
    }

    @Override
    public void deleteFile(String key, String folder) {

    }
}
