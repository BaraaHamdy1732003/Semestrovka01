package repository;

import models.FileInfo;

public interface FilesRepository {
    void save(FileInfo entity);
    FileInfo findById(Long Id);
}
