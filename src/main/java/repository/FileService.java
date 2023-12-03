package repository;

import models.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {
    void saveFileToStorage(InputStream file, String originalName,String ContentType,Long size);
    void writeFileFormStorage(Long fileId, OutputStream outputStream);
    FileInfo getFileInfo(Long fileId);
}
