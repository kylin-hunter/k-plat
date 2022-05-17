package com.kylinhunter.plat.generator.web.convertor.select;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @description
 * @author BiJi'an
 * @date   2021/8/6
 **/
public interface BranchBuilder<P, T> {

    /**
     * @param factory
     * @return com.kylinhunter.plat.generator.cskb.convertor.select.Branch<P, T>
     * @throws
     * @title 使用一个值工厂构造出一个分支
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 4:49 下午
     */
    Branch<P, T> then(Function<P, T> factory);

    /**
     * @param value
     * @return com.kylinhunter.plat.generator.cskb.convertor.select.Branch<P, T>
     * @throws
     * @title 从值构建出一个分支
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 4:50 下午
     */
    default Branch<P, T> then(T value) {
        return then(p -> value);
    }

    /**
     * @param tester
     * @return com.kylinhunter.plat.generator.cskb.convertor.select.BranchBuilder<P, T>
     * @throws
     * @title 工厂函数，用于创建分支构建者
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 4:50 下午
     */
    static <P, T> BranchBuilder<P, T> of(Predicate<P> tester) {
        return factory -> Branch.of(tester, factory);
    }

}
