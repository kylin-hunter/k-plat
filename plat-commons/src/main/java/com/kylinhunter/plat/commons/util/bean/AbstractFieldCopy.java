package com.kylinhunter.plat.commons.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.kylinhunter.plat.commons.exception.inner.InternalException;
import com.kylinhunter.plat.commons.util.convertor.FieldCopy;

import lombok.Data;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-13 19:09
 **/
@Data
public abstract class AbstractFieldCopy implements FieldCopy {

    protected final Method sourceMethod;
    protected final Method targetMethod;
    protected final boolean reverse;
    protected final Field sourceField;
    protected final Field targetField;

    public Object getSourceValue(Object source) {
        try {
            return sourceMethod.invoke(source);
        } catch (Exception e) {
            throw new InternalException("getSourceValue error", e);
        }

    }

    public void setTargetValue(Object target, Object value) {
        try {
            targetMethod.invoke(target, value);
        } catch (Exception e) {
            throw new InternalException("setTargetValue error", e);
        }
    }

}
