package com.kylinhunter.plat.commons.util.convertor;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-13 19:09
 **/

public interface ClassCopy<S, T> {
    void convert(Object source, Object target);

    void convertForword(S source, T target);

    void convertBackward(T target, S source);
}
