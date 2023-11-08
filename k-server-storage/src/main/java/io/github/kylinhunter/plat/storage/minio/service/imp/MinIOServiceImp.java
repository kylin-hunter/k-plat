/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.storage.minio.service.imp;

import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileMetadata;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqCreate;
import io.github.kylinhunter.plat.storage.exception.StorageException;
import io.github.kylinhunter.plat.storage.minio.service.MinIOService;
import io.github.kylinhunter.plat.storage.service.local.imp.AbstractStorageService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-27 01:29
 */
@Component
@RequiredArgsConstructor
@ConditionalOnExpression("'${kplat.storage.type}' == 'minio'")
@Slf4j
public class MinIOServiceImp extends AbstractStorageService implements MinIOService {
  private final MinioClient minioClient;
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
    int partSize = DEFAULT_PART_SIZE;
    if (objectSize > MIDDLE_SIZE && objectSize < LARGE_SIZE) {
      partSize = MIDDLE_PART_SIZE;
    } else if (objectSize > LARGE_SIZE) {
      partSize = LARGE_PART_SIZE;
    }
    return partSize;
  }

  private void upload(String bucket, String path, long objectSize, InputStream inputStream) {
    try {
      long partSize = calPartSzie(objectSize);
      minioClient.putObject(
          PutObjectArgs.builder().bucket(bucket).object(path).stream(
                  inputStream, objectSize <= 0 ? -1 : objectSize, partSize)
              .build());
    } catch (Exception e) {
      throw new StorageException("needUpload error", e);
    }
  }

  @Override
  public void upload(FileMetadataReqCreate fileMetadataReqCreate, InputStream inputStream) {
    final String path = fileMetadataReqCreate.getPath();
    final Long size = fileMetadataReqCreate.getSize();
    final String bucket = fileMetadataReqCreate.getBucket();
    this.upload(bucket, path, size, inputStream);
  }

  @Override
  public boolean delete(FileMetadata fileMetadata) {
    try {
      final String bucket = fileMetadata.getBucket();
      final String path = fileMetadata.getPath();
      minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(path).build());
      return true;
    } catch (Exception e) {
      log.error("delete error", e);
    }
    return false;
  }

  @Override
  public InputStream getDownloadInputStream(FileMetadata fileMetadata) {
    try {
      final String bucket = fileMetadata.getBucket();
      final String path = fileMetadata.getPath();
      return minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(path).build());
    } catch (Exception e) {
      e.printStackTrace();
      throw new StorageException("download error", e);
    }
  }
}
