package com.kylinhunter.plat.web.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import com.kylinhunter.plat.commons.util.EnumUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-21 19:50
 **/
@SuppressWarnings("NullableProblems")
@Slf4j
public class KplatLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        Locale locale = Lang.ZH.getLocale();
        String xlang = httpServletRequest.getHeader("X-LANG");
        if (!StringUtils.isEmpty(xlang)) {
            Lang lang = EnumUtil.fromName(Lang.class, xlang.toUpperCase(), false);
            if (lang != null) {
                locale = lang.getLocale();
            }
        }

        log.info("resolveLocale" + locale);
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                          Locale locale) {

    }
}
