package com.kylinhunter.plat.generator.core.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BiJi'an
 * @description 策略配置项
 * @date 2021/8/4
 **/
@Data
@Accessors(chain = true)
public class StrategyConfigs {
    private Map<Template, StrategyConfig> strategies = Maps.newHashMap();
    private List<Class<?>> entityClasses = Lists.newArrayList();

    public StrategyConfigs() {
        Arrays.stream(Template.values()).forEach(t -> {
            setStrategyConfig(t, new StrategyConfig(t));
        });
    }

    /**
     * @param entityClass
     * @return void
     * @throws
     * @title 添加entity
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 4:47 下午
     */
    public void addEntityClass(Class<?> entityClass) {
        this.entityClasses.add(entityClass);
    }

    public StrategyConfig get(Template template) {
        return strategies.get(template);
    }

    public StrategyConfigs setStrategyConfig(Template template, StrategyConfig strategyConfig) {
        strategies.put(template, strategyConfig);
        return this;
    }

}
