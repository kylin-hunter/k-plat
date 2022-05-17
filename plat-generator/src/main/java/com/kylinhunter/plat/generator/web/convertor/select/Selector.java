package com.kylinhunter.plat.generator.web.convertor.select;

import java.util.function.Function;

import lombok.ToString;

/**
 * @description 分支结果选择器
 * @author BiJi'an
 * @date   2021/8/6
 **/
@ToString(of = "selected")
public class Selector<P, T> {
    private boolean selected = false;
    private Function<P, T> factory;

    private final P param;/* 选择器参数，该参数会在进行条件判断和结果获取时会被当做条件传入*/

    public Selector(P param) {
        this.param = param;
    }

    /**
     * @param param
     * @return com.kylinhunter.plat.generator.cskb.convertor.select.Selector<P, T>
     * @throws
     * @title 使用指定的参数创建选择器
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 4:51 下午
     */
    public static <P, T> Selector<P, T> param(P param) {
        return new Selector<>(param);
    }

    /**
     * @param branch
     * @return com.kylinhunter.plat.generator.cskb.convertor.select.Selector<P, T>
     * @throws
     * @title 传入一个新的分支，判定这个分支满足条件
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 4:51 下午
     */
    public Selector<P, T> test(Branch<P, T> branch) {
        if (!selected) {
            boolean pass = branch.tester().test(param);
            if (pass) {
                selected = true;
                factory = branch.factory();
            }
        }
        return this;
    }

    /**
     * @param supplier
     * @return T
     * @throws
     * @title 获取结果，如果当前选择器没有击中任何条件分支，则从给定的提供者中获取结果；
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 4:52 下午
     */
    public T withDefault(Function<P, T> supplier) {
        return selected ? this.factory.apply(param) : supplier.apply(param);
    }

    /**
     * @return boolean
     * @throws
     * @title 当前选择器是否已经选择分支
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 4:52 下午
     */
    public boolean isSelected() {
        return selected;
    }

}
