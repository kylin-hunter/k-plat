package com.kylinhunter.plat.data.exception;

import com.kylinhunter.plat.commons.exception.info.ErrInfo;
import com.kylinhunter.plat.commons.exception.info.ErrInfoClassify;
import com.kylinhunter.plat.commons.exception.info.ErrInfoCustomizer;
import com.kylinhunter.plat.commons.exception.info.ErrInfoManager;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 00:53
 **/
public class DataErrInfoCustomizer implements ErrInfoCustomizer {

    private static final ErrInfoClassify CLASSIFY_DATA = new ErrInfoClassify(30001);
    public static final ErrInfo DUPLICATE = new ErrInfo(CLASSIFY_DATA);

    @Override
    public void customize(ErrInfoManager errInfoManager) {
        errInfoManager.init(DataErrInfoCustomizer.class);
    }
}
