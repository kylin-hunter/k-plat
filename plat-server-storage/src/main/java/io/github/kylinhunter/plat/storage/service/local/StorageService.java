package io.github.kylinhunter.plat.storage.service.local;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String upload(File file);

    String upload(MultipartFile multipartFile);

    void download(String id);

    boolean exist(String id);

    boolean delete(String id);
}
