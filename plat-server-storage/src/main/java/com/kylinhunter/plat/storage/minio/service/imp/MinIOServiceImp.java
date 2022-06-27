package com.kylinhunter.plat.storage.minio.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.storage.exception.StorageException;
import com.kylinhunter.plat.storage.minio.service.MinIOService;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-27 01:29
 **/
@Component
@RequiredArgsConstructor
public class MinIOServiceImp implements MinIOService {
    private final MinioClient minioClient;

    public void upload(String bucket, String path, String fileName, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(path + fileName)
                    .stream(fileInputStream, file.length(), -1)
                    .build());
        } catch (Exception e) {
            throw new StorageException("upload error", e);
        }

    }

    public InputStream download(String repository, String path, String fileName) {
        try {
            InputStream inputStream =
                    minioClient.getObject(GetObjectArgs.builder().bucket(repository).object(path + fileName).build());
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
            throw new StorageException("download error", e);
        }
    }

    public void remove(String repository, String path, String fileName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(repository).object(path + fileName).build());
        } catch (Exception e) {
            throw new StorageException("delete error", e);
        }
    }

}
