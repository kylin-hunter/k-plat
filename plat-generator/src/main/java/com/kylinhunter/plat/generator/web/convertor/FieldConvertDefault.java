package com.kylinhunter.plat.generator.web.convertor;

import static com.kylinhunter.plat.generator.web.convertor.select.Selectors.containsAny;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.kylinhunter.plat.generator.constants.VoSupport;
import com.kylinhunter.plat.generator.constants.VoType;
import com.kylinhunter.plat.generator.web.configuration.StrategyConfig;
import com.kylinhunter.plat.generator.web.convertor.select.Selectors;
import com.kylinhunter.plat.generator.web.pojo.EntityField;
import com.kylinhunter.plat.commons.util.ClassUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * @description 默认字段转换
 * @author BiJi'an
 * @date   2021/8/6
 **/
public class FieldConvertDefault implements FieldConvert {
    private static final String SERIAL_VERSIONU_ID = "serialVersionUID";

    @Override
    public EntityField process(StrategyConfig strategyConfig, Field field) {
        return Selectors.use(field)
                .test(containsAny(strategyConfig.getSkipFields()).then(f -> null))
                .test(containsAny(LocalDate.class, LocalDateTime.class, Date.class)
                        .then(f -> toEntityFieldFromDate(strategyConfig, f)))
                .withDefault(f -> toEntityField(strategyConfig, f));
    }

    /**
     * @param strategyConfig
     * @param field
     * @return com.kylinhunter.plat.generator.cskb.pojo.EntityField
     * @throws
     * @title 日期的转换
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 5:00 下午
     */
    public EntityField toEntityFieldFromDate(StrategyConfig strategyConfig, Field field) {
        EntityField entityField = this.toEntityField(strategyConfig, field);
        if (entityField != null) {
            entityField.setDatetime(true);
        }
        return entityField;
    }

    /**
     * @param strategyConfig
     * @param field
     * @return com.kylinhunter.plat.generator.cskb.pojo.EntityField
     * @throws
     * @title 默认的转换
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 5:00 下午
     */
    public EntityField toEntityField(StrategyConfig strategyConfig, Field field) {
        EntityField entityField = new EntityField();
        if (SERIAL_VERSIONU_ID.equals(field.getName())) {
            return null;
        }
        entityField.setName(field.getName());
        entityField.setTypeFullName(field.getType().getCanonicalName());
        entityField.setTypeSimpleName(ClassUtils.getClassName(entityField.getTypeFullName()));
        ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
        if (apiModelProperty != null) {
            entityField.setComment(StringUtils.defaultIfBlank(apiModelProperty.value(), field.getName()));
        } else {
            entityField.setComment(field.getName());
        }
        entityField.setPrimitive(field.getType().isPrimitive());
        processVoType(entityField, field);
        return entityField;
    }

    private void processVoType(EntityField entityField, Field field) {
        VoSupport voSupport = field.getAnnotation(VoSupport.class);

        entityField.setSupportCreate(true);
        entityField.setSupportUpdate(true);
        entityField.setSupportResponse(true);
        entityField.setSupportQuery(false);
        if (voSupport != null) {
            VoType[] voTypes = voSupport.value();
            for (VoType voType : voTypes) {
                switch (voType) {
                    case CREATE: {
                        entityField.setSupportCreate(true);
                        break;
                    }
                    case UPDATE: {
                        entityField.setSupportUpdate(true);
                        break;
                    }
                    case RESPONSE: {
                        entityField.setSupportResponse(true);
                        break;
                    }
                    case QUERY: {
                        entityField.setSupportQuery(true);
                        break;
                    }
                }

            }
        }
    }

}
