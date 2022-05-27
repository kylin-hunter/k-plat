package com.kylinhunter.plat.generator.core.convertor.select;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @description
 * @author BiJi'an
 * @date   2022/01/01
 **/
public interface Branch<P, T> {

    Predicate<P> tester();

    Function<P, T> factory();

    /**
     * @param tester
     * @param factory
     * @return com.kylinhunter.plat.generator.cskb.convertor.select.Branch<P, T>
     * @throws
     * @title 参考 mybatis plus 创建分支
     * @description
     * @author BiJi'an
     * @date 2022/01/01 4:48 下午
     */
    static <P, T> Branch<P, T> of(Predicate<P> tester, Function<P, T> factory) {
        return new Branch<P, T>() {

            @Override
            public Predicate<P> tester() {
                return tester;
            }

            @Override
            public Function<P, T> factory() {
                return factory;
            }

        };
    }
}
