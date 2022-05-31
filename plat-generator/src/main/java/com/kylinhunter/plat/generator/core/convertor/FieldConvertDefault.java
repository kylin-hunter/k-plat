package com.kylinhunter.plat.generator.core.convertor;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.kylinhunter.plat.commons.tools.select.BranchBuilder;
import com.kylinhunter.plat.commons.tools.select.BranchExecutors;
import com.kylinhunter.plat.generator.core.configuration.StrategyConfig;
import com.kylinhunter.plat.generator.core.configuration.bean.EntityField;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author BiJi'an
 * @description 默认字段转换
 * @date 2022/01/01
 **/
public class FieldConvertDefault implements FieldConvert {
    private static final String SERIAL_VERSIONU_ID = "serialVersionUID";

    @Override
    public EntityField convert(StrategyConfig strategyConfig, Field field) {

        return BranchExecutors.use(field, EntityField.class)
                .test(
                        containsAny(strategyConfig.getSkipFields()).then(f -> null)
                )
                .test(
                        containsAny(LocalDate.class, LocalDateTime.class, Date.class)
                                .then(f -> processDate(strategyConfig, f))
                )
                .others(f -> processDefault(strategyConfig, f));
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
        return BranchBuilder.of(field -> skipFields.contains(field.getName()));
    }

    /**
     * @param strategyConfig strategyConfig
     * @param field          field
     * @return com.kylinhunter.plat.generator.cskb.pojo.EntityField
     * @title 日期的转换
     * @description
     * @author BiJi'an
     * @date 2022/01/01 5:00 下午
     */
    public EntityField processDate(StrategyConfig strategyConfig, Field field) {
        EntityField entityField = this.processDefault(strategyConfig, field);
        if (entityField != null) {
            entityField.setDatetime(true);
        }
        return entityField;
    }

    /**
     * @param strategyConfig strategyConfig
     * @param field          field
     * @return com.kylinhunter.plat.generator.cskb.pojo.EntityField
     * @title 默认的转换
     * @description
     * @author BiJi'an
     * @date 2022/01/01 5:00 下午
     */
    public EntityField processDefault(StrategyConfig strategyConfig, Field field) {
        EntityField entityField = new EntityField();
        if (SERIAL_VERSIONU_ID.equals(field.getName())) {
            return null;
        }
        entityField.setName(field.getName());
        entityField.setClassName(field.getType().getCanonicalName());
        entityField.setClassSimpleName(field.getType().getSimpleName());
        ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
        if (apiModelProperty != null) {
            entityField.setComment(StringUtils.defaultIfBlank(apiModelProperty.value(), field.getName()));
        } else {
            entityField.setComment(field.getName());
        }
        entityField.setPrimitive(field.getType().isPrimitive());
        return entityField;
    }

}
