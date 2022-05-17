package com.kylinhunter.plat.web.exception;

import com.kylinhunter.plat.commons.exception.info.ErrInfo;
import com.kylinhunter.plat.commons.exception.inner.biz.BizException;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-01 14:03
 **/
public class AuthException extends BizException {
    public AuthException(String message, Throwable cause) {
        this(WebErrInfos.AUTH_ERROR, message, cause);
    }

    public AuthException(ErrInfo errInfo) {
        this(errInfo, "");
    }

    public AuthException(String message) {
        this(WebErrInfos.AUTH_ERROR, message);
    }

    public AuthException(Throwable cause) {
        this(WebErrInfos.AUTH_ERROR, cause);
    }

    public AuthException(ErrInfo errInfo, String message, Throwable cause) {
        super(errInfo, message, cause);
    }

    public AuthException(ErrInfo errInfo, String message) {
        super(errInfo, message);
    }

    public AuthException(ErrInfo errInfo, Throwable cause) {
        super(errInfo, "", cause);
    }

}
