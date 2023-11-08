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
package io.github.kylinhunter.plat.storage.service.local.helper;

import io.github.kylinhunter.commons.date.DateFormats;
import io.github.kylinhunter.commons.date.DateUtils;
import io.github.kylinhunter.commons.exception.embed.KIOException;
import io.github.kylinhunter.commons.exception.embed.ParamException;
import io.github.kylinhunter.commons.utils.codec.MD5Utils;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqCreate;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-03 18:45
 */
public class FileMetadataHelper {

  public static FileMetadataReqCreate createFileMetadataReqCreate(MultipartFile multipartFile) {
    try {
      FileMetadataReqCreate fileMetadataReqCreate = new FileMetadataReqCreate();
      fileMetadataReqCreate.setType(0);
      fileMetadataReqCreate.setDescription("");
      try (InputStream inputStream = multipartFile.getInputStream()) {
        final String md5 = MD5Utils.md5(inputStream);
        fileMetadataReqCreate.setMd5(md5);
      }

      final long size = multipartFile.getSize();
      if (size <= 0) {
        throw new ParamException("size can't <=0");
      }
      fileMetadataReqCreate.setSize(size);

      fileMetadataReqCreate.setPath(FileMetadataHelper.createNewPath());
      final String contentType = multipartFile.getContentType();
      if (!StringUtils.isEmpty(contentType)) {
        fileMetadataReqCreate.setContentType(contentType);
      } else {
        fileMetadataReqCreate.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
      }
      final String filename = multipartFile.getOriginalFilename();
      if (StringUtils.isEmpty(filename)) {
        throw new ParamException(" file name can't be  empty");
      }
      fileMetadataReqCreate.setName(filename);
      fileMetadataReqCreate.setExtension(FilenameUtils.getExtension(filename));

      return fileMetadataReqCreate;
    } catch (IOException e) {
      throw new KIOException("createFileMetadataReqCreate error", e);
    }
  }

  public static FileMetadataReqCreate createFileMetadataReqCreate(File file) {
    FileMetadataReqCreate fileMetadataReqCreate = new FileMetadataReqCreate();
    fileMetadataReqCreate.setType(0);
    fileMetadataReqCreate.setDescription("");

    final String md5 = MD5Utils.md5(file);
    fileMetadataReqCreate.setMd5(md5);

    final long size = file.length();
    if (size <= 0) {
      throw new ParamException("size can't <=0");
    }
    fileMetadataReqCreate.setSize(size);
    fileMetadataReqCreate.setPath(FileMetadataHelper.createNewPath());

    fileMetadataReqCreate.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

    final String filename = file.getName();
    if (StringUtils.isEmpty(filename)) {
      throw new ParamException(" file name can't be  empty");
    }
    fileMetadataReqCreate.setName(filename);
    fileMetadataReqCreate.setExtension(FilenameUtils.getExtension(filename));

    return fileMetadataReqCreate;
  }

  public static String createNewPath() {
    return DateUtils.format(LocalDateTime.now(), DateFormats.DATE_SLASH)
        + "/"
        + UUID.randomUUID().toString();
  }
}
