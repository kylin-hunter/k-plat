package com.kylinhunter.plat.storage.minio.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqCreate;
import com.kylinhunter.plat.commons.codec.MD5Util;
import com.kylinhunter.plat.storage.config.StorageConfig;
import com.kylinhunter.plat.storage.exception.StorageException;
import com.kylinhunter.plat.storage.minio.service.MinIOService;
import com.kylinhunter.plat.storage.service.local.imp.AbstractStorageService;

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
@ConditionalOnExpression("'${kplat.storage.type}' == 'minio'")
public class MinIOServiceImp extends AbstractStorageService implements MinIOService {
    private final MinioClient minioClient;
    private final StorageConfig storageConfig;
    public static final int DEFAULT_PART_SIZE = 5 * 1024 * 1024;
    public static final int MIDDLE_SIZE = 512 * 1024 * 1024;
    public static final int MIDDLE_PART_SIZE = 10 * 1024 * 1024;
    public static final int LARGE_SIZE = 1024 * 1024 * 1024;
    public static final int LARGE_PART_SIZE = 20 * 1024 * 1024;

    /**
     * @param objectSize objectSize
     * @return long
     * @title calPartSzie
     * @description
     * @author BiJi'an
     * @date 2022-06-30 01:59
     */
    private long calPartSzie(long objectSize) {
        int partSize = -1;
        if (objectSize < 0) {
            partSize = DEFAULT_PART_SIZE;
        } else if (objectSize > MIDDLE_SIZE && objectSize < LARGE_SIZE) {
            partSize = MIDDLE_PART_SIZE;
        } else if (objectSize >= LARGE_SIZE) {
            partSize = LARGE_PART_SIZE;
        }
        return partSize;
    }

    @Override
    public void upload(String bucket, String path, long objectSize, InputStream inputStream) {
        try {


            if (StringUtils.isEmpty(bucket)) {
                bucket = storageConfig.getS3().getBucket();
            }
            long partSize = calPartSzie(objectSize);
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(path)
                    .stream(inputStream, objectSize, partSize)
                    .build());
        } catch (Exception e) {
            throw new StorageException("upload error", e);
        }
    }

    @Override
    public void upload(String path, long objectSize, InputStream inputStream) {
        this.upload(null, path, objectSize, inputStream);
    }

    @Override
    public void upload(String path, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            this.upload(path, file.length(), fileInputStream);
        } catch (Exception e) {
            throw new StorageException("upload error", e);
        }

    }

    @Override
    public void upload(String path, InputStream inputStream) {
        this.upload(path, -1, inputStream);
    }

    @Override
    public void upload(MultipartFile multipartFile) {

        try (InputStream inputStream = multipartFile.getInputStream()) {
            String md5 = MD5Util.md5(multipartFile.getBytes());
            FileMetadataReqCreate fileMetadataReqCreate = new FileMetadataReqCreate();
            fileMetadataReqCreate.setMd5(md5);
            fileMetadataReqCreate.setPath(UUID.randomUUID().toString());
            fileMetadataReqCreate.setContentType(multipartFile.getContentType());
            fileMetadataReqCreate.setName(multipartFile.getOriginalFilename());
            fileMetadataReqCreate.setExtension(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));

            //            this.upload(path, multipartFile.getSize(), inputStream);
        } catch (Exception e) {
            throw new StorageException("upload error", e);
        }
    }

    public InputStream download(String bucket, String path) {
        try {
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(path).build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new StorageException("download error", e);
        }
    }

    public void delete(String repository, String path, String fileName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(repository).object(path + fileName).build());
        } catch (Exception e) {
            throw new StorageException("delete error", e);
        }
    }

}
