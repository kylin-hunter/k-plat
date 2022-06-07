package com.kylinhunter.plat.web.exception;

import com.kylinhunter.plat.commons.exception.info.ErrInfo;
import com.kylinhunter.plat.commons.exception.info.ErrInfoClassify;
import com.kylinhunter.plat.commons.exception.info.ErrInfoCustomizer;
import com.kylinhunter.plat.commons.exception.info.ErrInfoManager;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 00:53
 **/
public class WebErrInfoCustomizer implements ErrInfoCustomizer {

    private static final ErrInfoClassify CLASSIFY_AUTH = new ErrInfoClassify(20001);

    public static final ErrInfo AUTH_ERROR = new ErrInfo(CLASSIFY_AUTH);
    public static final ErrInfo AUTH_TOKEN_CREATE_ERROR = new ErrInfo(CLASSIFY_AUTH);
    public static final ErrInfo AUTH_TOKEN_VERIFY_NOT_FOUND = new ErrInfo(CLASSIFY_AUTH);
    public static final ErrInfo AUTH_TOKEN_VERIFY_EXPIRED = new ErrInfo(CLASSIFY_AUTH);
    public static final ErrInfo AUTH_TOKEN_VERIFY_INVALID = new ErrInfo(CLASSIFY_AUTH);
    public static final ErrInfo AUTH_TOKEN_VERIFY_ERROR = new ErrInfo(CLASSIFY_AUTH);

    private static final ErrInfoClassify CLASSIFY_WEB = new ErrInfoClassify(20002);
    public static final ErrInfo WEB_NOT_SUPPORTED = new ErrInfo(CLASSIFY_WEB);
    public static final ErrInfo WEB_NO_HANDLER_FOUND = new ErrInfo(CLASSIFY_WEB);

    @Override
    public void customize(ErrInfoManager errInfoManager) {
        errInfoManager.init(WebErrInfoCustomizer.class);
    }
}
