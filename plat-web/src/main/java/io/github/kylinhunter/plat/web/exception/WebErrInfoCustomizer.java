package io.github.kylinhunter.plat.web.exception;

import io.github.kylinhunter.commons.exception.info.ErrInfo;
import io.github.kylinhunter.commons.exception.info.ErrInfoAware;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 00:53
 **/
@ErrInfoAware
public class WebErrInfoCustomizer {

    static int BASE_CODE = 60000;
    public static final ErrInfo AUTH_ERROR = new ErrInfo(++BASE_CODE);
    public static final ErrInfo AUTH_TOKEN_CREATE_ERROR = new ErrInfo(++BASE_CODE);
    public static final ErrInfo AUTH_TOKEN_VERIFY_NOT_FOUND = new ErrInfo(++BASE_CODE);
    public static final ErrInfo AUTH_TOKEN_VERIFY_EXPIRED = new ErrInfo(++BASE_CODE);
    public static final ErrInfo AUTH_TOKEN_VERIFY_INVALID = new ErrInfo(++BASE_CODE);
    public static final ErrInfo AUTH_TOKEN_VERIFY_ERROR = new ErrInfo(++BASE_CODE);

    public static final ErrInfo WEB_NOT_SUPPORTED = new ErrInfo(++BASE_CODE);
    public static final ErrInfo WEB_NO_HANDLER_FOUND = new ErrInfo(++BASE_CODE);

}
