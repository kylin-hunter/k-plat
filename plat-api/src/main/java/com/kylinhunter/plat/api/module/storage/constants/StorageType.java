package com.kylinhunter.plat.api.module.storage.constants;

import com.kylinhunter.plat.commons.util.EnumUtil;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-30 00:40
 **/
@Getter
@RequiredArgsConstructor
public enum StorageType implements EnumUtil.EnumCode {
    MINIO(0, "MINIO");
    private final int code;
    private final String name;

}