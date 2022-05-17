package com.kylinhunter.plat.commons.util.rules;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import jodd.util.StringPool;
import jodd.util.StringUtil;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-10 14:39
 **/
public class NCUnderlineToCamelLowercaseFirst implements NamingConvertors.NamingConvertor {
    @Override
    public String convert(String name) {
        if (StringUtils.isBlank(name)) {
            return StringPool.EMPTY;
        }
        StringBuilder result = new StringBuilder();
        String[] camels = name.split(StringPool.UNDERSCORE);
        // 处理驼峰片段
        Arrays.stream(camels).filter(camel -> !StringUtil.isBlank(camel)).forEach(camel -> {
            result.append(camel.substring(0, 1).toUpperCase() + camel.substring(1).toLowerCase());
        });
        result.setCharAt(0, Character.toLowerCase(result.charAt(0)));
        return result.toString();

    }

}
