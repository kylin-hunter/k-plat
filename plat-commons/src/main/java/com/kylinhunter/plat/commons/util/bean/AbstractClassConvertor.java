package com.kylinhunter.plat.commons.util.bean;


import com.kylinhunter.plat.commons.util.convertor.ClassCopy;

import lombok.Data;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-13 19:09
 **/
@Data
public abstract class AbstractClassConvertor<S, T> implements ClassCopy<S, T> {
    protected final boolean reverse;

    @Override
    public void convert(Object source, Object target) {
        if (reverse) {
            this.convertBackward((T) source, (S) target);
        } else {
            this.convertForword((S) source, (T) target);
        }
    }
}
