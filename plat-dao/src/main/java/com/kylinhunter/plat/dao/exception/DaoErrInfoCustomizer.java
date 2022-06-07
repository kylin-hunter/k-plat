package com.kylinhunter.plat.dao.exception;

import com.kylinhunter.plat.commons.exception.info.ErrInfo;
import com.kylinhunter.plat.commons.exception.info.ErrInfoClassify;
import com.kylinhunter.plat.commons.exception.info.ErrInfoCustomizer;
import com.kylinhunter.plat.commons.exception.info.ErrInfoManager;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 00:53
 **/
public class DaoErrInfoCustomizer implements ErrInfoCustomizer {

    private static final ErrInfoClassify CLASSIFY_DB = new ErrInfoClassify(30001);
    public static final ErrInfo DUPLICATE = new ErrInfo(CLASSIFY_DB);
    public static final ErrInfo CONSTRAINT = new ErrInfo(CLASSIFY_DB);
    public static final ErrInfo CONSTRAINT_FOREIGN = new ErrInfo(CLASSIFY_DB);

    @Override
    public void customize(ErrInfoManager errInfoManager) {
        errInfoManager.init(DaoErrInfoCustomizer.class);
    }
}
