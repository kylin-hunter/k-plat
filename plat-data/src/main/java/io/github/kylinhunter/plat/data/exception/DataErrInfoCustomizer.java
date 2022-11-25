package io.github.kylinhunter.plat.data.exception;

import io.github.kylinhunter.commons.exception.info.ErrInfo;
import io.github.kylinhunter.commons.exception.info.ErrInfoAware;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 00:53
 **/
@ErrInfoAware
public class DataErrInfoCustomizer {
    static int BASE_CODE = 30000;

    public static final ErrInfo DUPLICATE = new ErrInfo(++BASE_CODE);

}
