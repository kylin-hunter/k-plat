package com.kylinhunter.plat.storage.service.local.dto;

import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileMetadata;

import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-05 00:43
 **/
@Data
public class UploadBeforeMsg {

    private boolean needUpload; // 是否上传
    private FileMetadata oldFileMetadata;
}
