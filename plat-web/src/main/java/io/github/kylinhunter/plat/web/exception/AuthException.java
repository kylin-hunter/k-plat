package io.github.kylinhunter.plat.web.exception;

import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.commons.exception.info.ErrInfo;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-01 14:03
 **/
public class AuthException extends BizException {
    public AuthException(String message, Throwable cause) {
        this(WebErrInfoCustomizer.AUTH_ERROR, message, cause);
    }

    public AuthException(ErrInfo errInfo) {
        this(errInfo, "");
    }

    public AuthException(String message) {
        this(WebErrInfoCustomizer.AUTH_ERROR, message);
    }

    public AuthException(Throwable cause) {
        this(WebErrInfoCustomizer.AUTH_ERROR, cause);
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
