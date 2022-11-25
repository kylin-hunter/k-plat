package io.github.kylinhunter.plat.api.module.storage.constants;


import io.github.kylinhunter.commons.util.EnumUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-30 00:40
 **/
@Getter
@RequiredArgsConstructor
public enum StorageType implements EnumUtils.EnumCode {
    MINIO(0, "MINIO");
    private final int code;
    private final String name;

}