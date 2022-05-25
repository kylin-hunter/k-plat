package com.kylinhunter.plat.generator.core.convertor.select;

import java.lang.reflect.Field;
import java.util.Set;

import com.kylinhunter.plat.generator.core.configuration.bean.EntityField;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-06 17:05
 **/
public class Selectors {

    /**
     * @param field
     * @return com.kylinhunter.plat.generator.cskb.convertor.select.Selector
     * <java.lang.reflect.Field, com.kylinhunter.plat.generator.cskb.pojo.EntityField>
     * @throws
     * @title 构造一个选择器
     * @description
     * @author BiJi'an
     * @updateTime 2022/01/01 5:15 下午
     */
    public static Selector<Field, EntityField> use(Field field) {
        return new Selector(field);
    }

    public static BranchBuilder<Field, EntityField> contains(Class clazz) {
        return BranchBuilder.of(field -> field.getType() == clazz);
    }

    public static BranchBuilder<Field, EntityField> containsAny(Class... classes) {
        return BranchBuilder.of(field -> {
            for (Class clazz : classes) {
                if (field.getType() == clazz) {
                    return true;
                }
            }
            return false;
        });
    }

    public static BranchBuilder<Field, EntityField> containsAny(Set<String> skipFields) {
        return BranchBuilder.of(field -> {
            return skipFields.contains(field.getName());
        });
    }
}
