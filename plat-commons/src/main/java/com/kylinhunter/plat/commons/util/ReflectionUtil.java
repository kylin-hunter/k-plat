package com.kylinhunter.plat.commons.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-04 17:04
 **/
@Slf4j
public class ReflectionUtil {

    private static final Map<String, List<Field>> ALL_FIELDS = Maps.newHashMap();

    private static final Map<String, Map<String, Field>> ALL_FIELDS_MAP = Maps.newHashMap();

    public static Map<String, Field> getAllDeclaredFieldMaps(@NonNull Class<?> clazz,
                                                             FieldScope fieldScope) {
        String key = clazz.getName() + fieldScope.name();
        return ALL_FIELDS_MAP.compute(key, (k, v) -> {
            if (v == null) {
                v = Maps.newHashMap();
                List<Field> fields = getDeclaredFields(clazz, fieldScope);
                for (Field field : fields) {
                    v.put(field.getName(), field);
                }
            }
            return v;
        });
    }

    public static List<Field> getAllDeclaredFields(@NonNull Class<?> clazz, FieldScope fieldScope) {

        String key = clazz.getName() + fieldScope.name();
        return ALL_FIELDS.compute(key, (k, v) -> {
            if (v == null) {
                v = getDeclaredFields(clazz, fieldScope);
            }
            return v;
        });

    }

    private static List<Field> getDeclaredFields(@NonNull Class<?> clazz, FieldScope fieldScope) {
        if (FieldScope.THIS == fieldScope) {
            return Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList());
        } else {
            List<Field> fieldList = FieldScope.ALLL == fieldScope ?
                    Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList())
                    : new ArrayList<>();

            Class tempClass = clazz.getSuperclass();
            while (tempClass != null && !tempClass.getName().toLowerCase().equals("java.lang.object")) {
                fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
            }

            return fieldList;
        }

    }

    public static enum FieldScope {
        THIS, PARENT, ALLL;
    }

}
