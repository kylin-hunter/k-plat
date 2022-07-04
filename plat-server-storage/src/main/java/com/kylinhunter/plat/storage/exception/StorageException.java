package com.kylinhunter.plat.storage.exception;

import com.kylinhunter.plat.commons.exception.info.ErrInfo;
import com.kylinhunter.plat.commons.exception.inner.biz.BizException;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 14:03
 **/
public class StorageException extends BizException {
    public StorageException(String message, Throwable cause) {
        this(StorageErrInfoCustomizer.STORAGE_ERROR, message, cause);
    }

    public StorageException(ErrInfo errInfo) {
        this(errInfo, "");
    }

    public StorageException(String message) {
        this(StorageErrInfoCustomizer.STORAGE_ERROR, message);
    }

    public StorageException(Throwable cause) {
        this(StorageErrInfoCustomizer.STORAGE_ERROR, cause);
    }

    public StorageException(ErrInfo errInfo, String message, Throwable cause) {
        super(errInfo, message, cause);
    }

    public StorageException(ErrInfo errInfo, String message) {
        super(errInfo, message);
    }

    public StorageException(ErrInfo errInfo, Throwable cause) {
        super(errInfo, "", cause);
    }

}