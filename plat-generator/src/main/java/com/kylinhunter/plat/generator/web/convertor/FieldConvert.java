package com.kylinhunter.plat.generator.web.convertor;

import java.lang.reflect.Field;

import com.kylinhunter.plat.generator.web.configuration.StrategyConfig;
import com.kylinhunter.plat.generator.web.pojo.EntityField;

/**
 * @description 字段转换
 * @author BiJi'an
 * @date   2021/8/6
 **/
public interface FieldConvert {
    /**
     * @param strategyConfig
     * @param field
     * @return com.kylinhunter.plat.generator.custom.convertor.EntityField
     * @throws
     * @title 类型转换
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 11:18 上午
     */
    EntityField process(StrategyConfig strategyConfig, Field field);

}
