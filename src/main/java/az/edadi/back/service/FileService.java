package az.edadi.back.service;

import az.edadi.back.constants.PhotoEnum;

import java.io.File;

public interface FileService {

    String saveFile(String key, File file, PhotoEnum folder);

    String update(String key, File file);

    void deleteFile(String key, PhotoEnum folder);
}
