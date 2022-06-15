package com.kylinhunter.plat.core.init.initializer;

import java.util.Set;

import org.springframework.beans.factory.InitializingBean;

import com.google.common.collect.Sets;
import com.kylinhunter.plat.commons.exception.inner.InitException;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-16 01:49
 **/

public abstract class BasicInitializer implements Initializer, InitializingBean {
    private int order;

    private final static Set<Integer> orders = Sets.newHashSet();

    public abstract int order();

    public void afterPropertiesSet() {
        this.order = order();
        if (orders.contains(order)) {
            throw new InitException("invalid order" + order);
        }
        orders.add(order);
    }

}
