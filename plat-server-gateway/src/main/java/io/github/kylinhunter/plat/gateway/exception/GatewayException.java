package io.github.kylinhunter.plat.gateway.exception;

import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.commons.exception.info.ErrInfo;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 14:03
 **/
public class GatewayException extends BizException {
    public GatewayException(String message, Throwable cause) {
        this(GatewayErrInfoCustomizer.STORAGE_ERROR, message, cause);
    }

    public GatewayException(ErrInfo errInfo) {
        this(errInfo, "");
    }

    public GatewayException(String message) {
        this(GatewayErrInfoCustomizer.STORAGE_ERROR, message);
    }

    public GatewayException(Throwable cause) {
        this(GatewayErrInfoCustomizer.STORAGE_ERROR, cause);
    }

    public GatewayException(ErrInfo errInfo, String message, Throwable cause) {
        super(errInfo, message, cause);
    }

    public GatewayException(ErrInfo errInfo, String message) {
        super(errInfo, message);
    }

    public GatewayException(ErrInfo errInfo, Throwable cause) {
        super(errInfo, "", cause);
    }

}
