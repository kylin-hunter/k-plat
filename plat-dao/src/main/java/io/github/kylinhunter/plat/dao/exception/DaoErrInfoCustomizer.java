package io.github.kylinhunter.plat.dao.exception;

import io.github.kylinhunter.commons.exception.info.ErrInfo;
import io.github.kylinhunter.commons.exception.info.ErrInfoAware;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 00:53
 **/
@ErrInfoAware
public class DaoErrInfoCustomizer {

    static int BASE_CODE = 50000;
    public static final ErrInfo DUPLICATE = new ErrInfo(++BASE_CODE);
    public static final ErrInfo CONSTRAINT = new ErrInfo(++BASE_CODE);
    public static final ErrInfo CONSTRAINT_FOREIGN = new ErrInfo(++BASE_CODE);

}
