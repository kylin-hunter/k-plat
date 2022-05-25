package com.kylinhunter.plat.generator.core.convertor;

import java.lang.reflect.Field;

import com.kylinhunter.plat.generator.core.configuration.StrategyConfig;
import com.kylinhunter.plat.generator.core.configuration.bean.EntityField;

/**
 * @description 字段转换
 * @author BiJi'an
 * @date   2022/01/01
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
     * @updateTime 2022/01/01 11:18 上午
     */
    EntityField process(StrategyConfig strategyConfig, Field field);

}
