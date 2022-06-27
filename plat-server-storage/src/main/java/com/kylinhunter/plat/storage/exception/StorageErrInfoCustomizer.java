package com.kylinhunter.plat.storage.exception;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.commons.exception.info.ErrInfo;
import com.kylinhunter.plat.commons.exception.info.ErrInfoClassify;
import com.kylinhunter.plat.commons.exception.info.ErrInfoCustomizer;
import com.kylinhunter.plat.commons.exception.info.ErrInfoManager;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 00:53
 **/
@Component
public class StorageErrInfoCustomizer implements ErrInfoCustomizer {

    private static final ErrInfoClassify CLASSIFY_STORAGE = new ErrInfoClassify(30001);

    public static final ErrInfo STORAGE_ERROR = new ErrInfo(CLASSIFY_STORAGE);
    ;

    @Override
    public void customize(ErrInfoManager errInfoManager) {
        errInfoManager.init(StorageErrInfoCustomizer.class);
    }
}
