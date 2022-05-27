package com.kylinhunter.plat.generator.core.convertor;

import java.lang.reflect.Field;

import com.kylinhunter.plat.generator.core.configuration.StrategyConfig;
import com.kylinhunter.plat.generator.core.configuration.bean.EntityField;

/**
 * @author BiJi'an
 * @description 字段转换
 * @date 2022/01/01
 **/
public interface FieldConvert {
    /**
     * @param strategyConfig strategyConfig
     * @param field          field
     * @return com.kylinhunter.plat.generator.custom.convertor.EntityField
     * @title 类型转换
     * @description
     * @author BiJi'an
     * @date 2022/01/01 11:18 上午
     */
    EntityField convert(StrategyConfig strategyConfig, Field field);

}
