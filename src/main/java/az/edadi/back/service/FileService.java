package az.edadi.back.service;

import java.io.File;

public interface FileService {
     String save(String key, File file);
     String update (String key);
}
