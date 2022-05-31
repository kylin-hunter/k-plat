package com.kylinhunter.plat.generator.core.convertor;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.kylinhunter.plat.commons.tools.select.BranchExecutor;
import com.kylinhunter.plat.commons.tools.select.BranchExecutors;
import com.kylinhunter.plat.generator.core.configuration.StrategyConfig;
import com.kylinhunter.plat.generator.core.configuration.bean.EntityField;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author BiJi'an
 * @description 默认字段转换
 * @date 2022/01/01
 **/
public class DefaultFieldConvert implements FieldConvert {
    private static final String SERIAL_VERSIONU_ID = "serialVersionUID";

    @Override
    public EntityField convert(StrategyConfig strategyConfig, Field field) {

        final BranchExecutor<Field, EntityField> branchExecutor = BranchExecutors.use(field, EntityField.class);
        return branchExecutor
                .test(
                        branchExecutor
                                .predicate(f -> strategyConfig.getSkipFields().contains(f.getName()))
                                .then(f -> null)
                ).others(f -> processDefault(strategyConfig, f));
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
    @SuppressWarnings("rawtypes")
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

        final Class<?>[] classes = new Class[] {LocalDate.class, LocalDateTime.class, Date.class};
        for (Class clazz : classes) {
            if (field.getType() == clazz) {
                entityField.setDatetime(true);

            }
        }
        return entityField;
    }

}
