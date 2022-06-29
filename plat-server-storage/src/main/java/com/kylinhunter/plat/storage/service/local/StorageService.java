package com.kylinhunter.plat.storage.service.local;

import java.io.File;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {



    void upload(String bucket, String path, long objectSize, InputStream inputStream);

    void upload(String path, long objectSize, InputStream inputStream);

    void upload(String path, File file);

    void upload(String path, InputStream inputStream);

    void upload(MultipartFile multipartFile);

}
