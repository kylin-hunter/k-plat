package com.kylinhunter.plat.commons.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.kylinhunter.plat.commons.util.JsonUtils;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-13 19:09
 **/

public class FieldCopyJson extends AbstractFieldCopy {

    private JavaType javaType;

    public FieldCopyJson(Method sourceMethod, Method targetMethod, boolean reverse, Field sourceField,
                         Field targetField) {
        super(sourceMethod, targetMethod, reverse, sourceField, targetField);
        Class<?> fieldType = targetField.getType();
        if (List.class.isAssignableFrom(fieldType)) {
            Type paramType = targetField.getGenericType();

            System.out.println("#" + paramType);
            if (paramType instanceof ParameterizedType) {
                Type[] genericTypes = ((ParameterizedType) paramType).getActualTypeArguments();

                if (genericTypes != null && genericTypes.length > 0) {
                    Type type = genericTypes[0];
                    if (type instanceof Class) {
                        Class clazz = (Class) type;
                        javaType = JsonUtils.constructParametricType(List.class, clazz);
                    }
                }
            }
        }

    }

    @Override
    public void convert(Object source, Object target) throws InvocationTargetException, IllegalAccessException {
        if (reverse) {
            Object sourceValue = this.getSourceValue(source);
            Object targetValue = null;
            if (javaType != null) {
                targetValue = JsonUtils.readValue(String.valueOf(sourceValue), javaType, false);
            } else {
                targetValue = JsonUtils.toObject(String.valueOf(sourceValue), this.targetField.getType(), false);
            }
            this.setTargetValue(target, targetValue);
        } else {
            Object sourceValue = this.getSourceValue(source);
            String targetValue = JsonUtils.toString(sourceValue, true);
            this.setTargetValue(target, targetValue);
        }

    }

}
