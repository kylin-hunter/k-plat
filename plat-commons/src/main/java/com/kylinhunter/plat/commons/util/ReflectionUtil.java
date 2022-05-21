package com.kylinhunter.plat.commons.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import com.kylinhunter.plat.commons.exception.inner.biz.ex.DBException;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 17:04
 **/
@Slf4j
public class ReflectionUtil {

    @SuppressWarnings("unchecked")
    private static final Map<String, Map<String, Field>> ALL_DECLARED_FIELDS = Maps.newHashMap();

    /**
     * @param clazz     clazz
     * @param withSuper withSuper
     * @return java.util.Map<java.lang.String, java.lang.reflect.Field>
     * @title getAllDeclaredFields
     * @description
     * @author BiJi'an
     * @date 2022-05-20 21:51
     */
    public static Map<String, Field> getAllDeclaredFields(@NonNull Class<?> clazz, boolean withSuper) {

        String key = clazz.getName() + withSuper;
        Map<String, Field> classFields = ALL_DECLARED_FIELDS.get(key);
        if (classFields == null) {
            synchronized(ReflectionUtil.class) {
                classFields = ALL_DECLARED_FIELDS.get(key);
                if (classFields != null) {
                    return classFields;
                } else {
                    List<Field> declaredFields = getDeclaredFields(clazz, withSuper);
                    classFields = declaredFields.stream().collect(Collectors.toMap(Field::getName, e -> e));
                    ALL_DECLARED_FIELDS.put(key, classFields);
                    return classFields;
                }
            }
        } else {
            return classFields;
        }

    }

    private static List<Field> getDeclaredFields(@NonNull Class<?> clazz, boolean withSuper) {
        if (withSuper) {
            List<Field> fieldList = Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList());
            Class<?> tempClass = clazz.getSuperclass();
            while (tempClass != null && !tempClass.getName().equalsIgnoreCase("java.lang.object")) {
                fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
            }
            return fieldList;
        } else {
            return Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList());
        }

    }

    public static <T> Class<T> getSuperClassGenericType(Class<?> clazz, int index) {
        try {
            Type superClass = clazz.getGenericSuperclass();
            return (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[index];

        } catch (Exception e) {
            throw new DBException("getEntityBean error", e);
        }
    }

    public static <T> Class<T> getGenericType(Field field, int index) {
        try {
            Type type = field.getGenericType();
            return (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[index];

        } catch (Exception e) {
            throw new DBException("getEntityBean error", e);
        }
    }

}
