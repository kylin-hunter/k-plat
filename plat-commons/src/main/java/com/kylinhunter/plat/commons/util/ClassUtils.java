package com.kylinhunter.plat.commons.util;

import org.apache.commons.lang3.StringUtils;

import jodd.util.StringPool;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-04 20:18
 **/
public class ClassUtils {

    /**
     * @param classPath
     * @return java.lang.String
     * @throws
     * @title 获取类名
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 8:18 下午
     */
    public static String getClassName(String classPath) {
        if (StringUtils.isBlank(classPath)) {
            return "";
        }
        return classPath.substring(classPath.lastIndexOf(StringPool.DOT) + 1);
    }
}
