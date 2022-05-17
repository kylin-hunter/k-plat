package com.kylinhunter.plat.commons.exception.info;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.kylinhunter.plat.commons.exception.inner.InitException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description 错误代码管理
 * @date 2022/01/01
 **/

@Slf4j
public class ErrCodeManager {
    public static final Map<Integer, String> ERR_CODES = Maps.newLinkedHashMap();
    private static volatile boolean initialized = false;

    public static String getDefaultMsg(int code) {
        return ERR_CODES.getOrDefault(code, ErrInfos.MSG_UNKNOWN);
    }

    static {
        init();
    }

    public static void register(int code, String defaultMsg) {
        if (ERR_CODES.containsKey(code)) {
            throw new InitException(" error code is used:" + code);
        } else {
            ERR_CODES.put(code, defaultMsg);
        }
    }

    public static synchronized void init(Class cls) {
        for (Field field : cls.getDeclaredFields()) {
            if (field.getType() == ErrInfo.class && Modifier.isFinal(field.getModifiers())) {
                try {
                    ErrInfo errInfo = (ErrInfo) field.get(null);
                    if (StringUtils.isEmpty(errInfo.getDefaultMsg())) {
                        errInfo.setDefaultMsg(field.getName());
                    }
                    register(errInfo.getCode(), errInfo.getDefaultMsg());

                } catch (Exception e) {
                    throw new InitException("init ErrCodeManager error", e);
                }

            }
        }
    }

    public static synchronized void init() {
        if (!initialized) {
            initialized = true;
            init(ErrInfos.class);
        }

    }

    public static void println() {
        log.info("print err code ");
        ERR_CODES.forEach((errCode, defaultMsg) -> log.info("erroCode={},defaultMsg={}", errCode, defaultMsg));

    }

}
