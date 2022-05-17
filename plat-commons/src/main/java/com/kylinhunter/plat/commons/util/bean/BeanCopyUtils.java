package com.kylinhunter.plat.commons.util.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.google.common.collect.Maps;
import com.kylinhunter.plat.commons.util.convertor.ClassConvertor;
import com.kylinhunter.plat.commons.util.convertor.ClassCopy;
import com.kylinhunter.plat.commons.util.convertor.FieldConvertor;
import com.kylinhunter.plat.commons.util.convertor.FieldCopy;
import com.kylinhunter.plat.commons.util.convertor.TargetType;
import com.kylinhunter.plat.commons.exception.inner.FormatException;
import com.kylinhunter.plat.commons.exception.inner.InitException;
import com.kylinhunter.plat.commons.util.ReflectionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-13 19:09
 **/
@Slf4j
public class BeanCopyUtils {
    public static final Map<String, BeanCopies> ALL_FIELD_COPYS = Maps.newHashMap();

    /**
     * @param source
     * @param target
     * @return void
     * @throws
     * @title 复制属性
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/13 8:52 下午
     */
    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        try {
            BeanUtils.copyProperties(source, target, ignoreProperties);
            BeanCopies beanCopies = getBeanCopies(source.getClass(), target.getClass());
            if (beanCopies.getClassCopy() != null) {
                beanCopies.getClassCopy().convert(source, target);
            }
            if (!beanCopies.isFieldEmpty()) {
                for (FieldCopy fieldCopy : beanCopies.getFieldCopies()) {
                    fieldCopy.convert(source, target);
                }
            }
        } catch (Exception e) {
            throw new FormatException("copyProperties error", e);
        }
    }

    /**
     * @param sourceClass
     * @param targetClass
     * @return com.kylinhunter.plat.commons.utils.bean.BeanCopies
     * @throws
     * @title 获取source的所有fieldcopy
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/13 8:47 下午
     */
    public static BeanCopies getBeanCopies(Class<?> sourceClass, Class<?> targetClass) {
        String key = sourceClass.getName() + targetClass.getName();
        BeanCopies beanCopies = ALL_FIELD_COPYS.get(key);
        if (beanCopies == null) {
            synchronized(BeanCopyUtils.class) {
                beanCopies = ALL_FIELD_COPYS.get(key);
                if (beanCopies == null) {
                    beanCopies = init(sourceClass, targetClass);
                    ALL_FIELD_COPYS.put(key, beanCopies);
                }
            }
        }
        return beanCopies;
    }

    private static BeanCopies init(Class<?> sourceClass, Class<?> targetClass) {
        BeanCopies beanCopies = new BeanCopies();
        initClassConvertor(beanCopies, sourceClass, targetClass);

        initFieldConvertor(beanCopies, sourceClass, targetClass);

        return beanCopies;

    }

    private static void initClassConvertor(BeanCopies beanCopies, Class<?> sourceClass, Class<?> targetClass) {

        ClassConvertor classConvertor = sourceClass.getAnnotation(ClassConvertor.class);
        try {
            if (classConvertor != null) {

                Class<?> convertor = Class.forName(classConvertor.value());
                final Constructor<?> constructor = convertor.getConstructor(boolean.class);
                beanCopies.setClassCopy((ClassCopy) constructor.newInstance(false));

            } else {
                classConvertor = targetClass.getAnnotation(ClassConvertor.class);
                if (classConvertor != null) {
                    Class<?>[] targets = classConvertor.targets();
                    if (Arrays.stream(targets).filter(e -> e == sourceClass).count() > 0) {
                        Class<?> convertor = Class.forName(classConvertor.value());
                        final Constructor<?> constructor = convertor.getConstructor(boolean.class);
                        beanCopies.setClassCopy((ClassCopy) constructor.newInstance(true));
                    }
                }

            }

        } catch (Exception e) {
            throw new InitException("init class convertor error", e);
        }
    }

    /**
     * @param sourceClass
     * @param targetClass
     * @return com.kylinhunter.plat.commons.utils.bean.BeanCopies
     * @throws
     * @title 初始化 filedcopy信息
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/13 8:47 下午
     */
    private static void initFieldConvertor(BeanCopies fileCopies, Class<?> sourceClass, Class<?> targetClass) {
        Map<String, Field> sourceFields =
                ReflectionUtil.getAllDeclaredFieldMaps(sourceClass, ReflectionUtil.FieldScope.ALLL);

        sourceFields.forEach((sourceFieldName, sourceField) -> {
            Map<String, Field> targetFields =
                    ReflectionUtil.getAllDeclaredFieldMaps(targetClass, ReflectionUtil.FieldScope.ALLL);
            Field targetField = targetFields.get(sourceFieldName);

            FieldConvertor fieldConvertor = sourceField.getAnnotation(FieldConvertor.class);

            if (fieldConvertor != null) {

                TargetType targetType = fieldConvertor.value();
                List<Class<?>> targets = Arrays.asList(fieldConvertor.targets());
                if (targetType == TargetType.JSON && targets.contains(targetClass)) {
                    PropertyDescriptor pdSource = BeanUtils.getPropertyDescriptor(sourceClass, sourceFieldName);
                    PropertyDescriptor pdTarget = BeanUtils.getPropertyDescriptor(targetClass, sourceFieldName);
                    if (pdSource != null && pdTarget != null) {
                        Method readMethod = pdSource.getReadMethod();
                        Method writeMethod = pdTarget.getWriteMethod();
                        if (readMethod != null && writeMethod != null) {
                            fileCopies.addFieldCopy(
                                    new FieldCopyJson(readMethod, writeMethod, false, sourceField, targetField));
                        }
                    }
                }
            } else {

                if (targetField != null) {
                    FieldConvertor targetFieldConvertor = targetField.getAnnotation(FieldConvertor.class);
                    if (targetFieldConvertor != null) {
                        Class<?>[] targets = targetFieldConvertor.targets();
                        if (Arrays.stream(targets).filter(e -> e == sourceClass).count() > 0) {
                            TargetType targetType = targetFieldConvertor.value();
                            if (targetType == TargetType.JSON) {
                                PropertyDescriptor pdSource =
                                        BeanUtils.getPropertyDescriptor(sourceClass, sourceFieldName);
                                PropertyDescriptor pdTarget =
                                        BeanUtils.getPropertyDescriptor(targetClass, sourceFieldName);
                                if (pdSource != null && pdTarget != null) {
                                    Method readMethod = pdSource.getReadMethod();
                                    Method writeMethod = pdTarget.getWriteMethod();
                                    if (readMethod != null && writeMethod != null) {
                                        fileCopies.addFieldCopy(
                                                new FieldCopyJson(readMethod, writeMethod, true, sourceField,
                                                        targetField));
                                    }
                                }
                            }

                        }
                    }

                }
            }
        });

    }

}
