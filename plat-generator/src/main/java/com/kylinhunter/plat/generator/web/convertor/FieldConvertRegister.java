package com.kylinhunter.plat.generator.web.convertor;

import java.util.Map;

import com.kylinhunter.plat.generator.web.configuration.TemplateType;
import com.google.common.collect.Maps;

/**
 * @description field转换器的注册工厂
 * @author BiJi'an
 * @date   2021/8/6
 **/
public class FieldConvertRegister {

    public static final FieldConvertDefault DEFAULT_TYPE_CONVERT = new FieldConvertDefault();
    private static final Map<TemplateType, FieldConvert> FIELD_CONVERTS = Maps.newHashMap();

    public static FieldConvert get(TemplateType templateType) {
        return FIELD_CONVERTS.getOrDefault(templateType, DEFAULT_TYPE_CONVERT);
    }

    /**
     * @param templateType
     * @param fieldConvert
     * @return void
     * @throws
     * @title 主从一个转换器
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 5:03 下午
     */
    public static void register(TemplateType templateType, FieldConvert fieldConvert) {
        if (fieldConvert != null) {
            FIELD_CONVERTS.put(templateType, fieldConvert);

        }
    }

}
