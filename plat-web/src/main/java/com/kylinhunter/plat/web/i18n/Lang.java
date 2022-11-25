package com.kylinhunter.plat.web.i18n;

import java.util.Locale;

import io.github.kylinhunter.commons.util.EnumUtils;
import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-07 16:34
 **/
@Getter
public enum Lang implements EnumUtils.EnumCode {
    ZH(1, "中文", Locale.CHINESE), EN(2, "英文", Locale.ENGLISH);
    private final int code;
    private final String name;
    private final Locale locale;

    Lang(int code, String name, Locale locale) {
        this.code = code;
        this.name = name;
        this.locale = locale;
    }

}
