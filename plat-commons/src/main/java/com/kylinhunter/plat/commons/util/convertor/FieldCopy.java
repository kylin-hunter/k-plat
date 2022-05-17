package com.kylinhunter.plat.commons.util.convertor;

import java.lang.reflect.InvocationTargetException;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-13 19:09
 **/
@FunctionalInterface
public interface FieldCopy {
    void convert(Object source, Object target) throws InvocationTargetException, IllegalAccessException;
}
