package com.kylinhunter.plat.commons.exception.inner;

import com.kylinhunter.plat.commons.exception.common.KRuntimeException;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;

/**
 * @author BiJi'an
 * @description
 * @date 2022/1/1
 **/

public class CommonIOException extends KRuntimeException {
    private static final long serialVersionUID = 1L;

    public CommonIOException() {
        this.errInfo = ErrInfos.IO;
    }

    public CommonIOException(String message) {
        super(ErrInfos.IO, message);
    }

    public CommonIOException(String message, Throwable e) {
        super(ErrInfos.IO, message, e);
    }
}
