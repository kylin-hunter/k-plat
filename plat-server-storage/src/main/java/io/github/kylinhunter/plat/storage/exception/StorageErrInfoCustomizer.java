package io.github.kylinhunter.plat.storage.exception;

import io.github.kylinhunter.commons.exception.info.ErrInfo;
import io.github.kylinhunter.commons.exception.info.ErrInfoAware;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 00:53
 **/
@ErrInfoAware
public class StorageErrInfoCustomizer {

    private static int BASE_CODE;
    public static final ErrInfo STORAGE_ERROR = new ErrInfo(++BASE_CODE);
}
