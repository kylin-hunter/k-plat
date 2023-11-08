package io.github.kylinhunter.plat.generator.auto.mybatis;

import io.github.kylinhunter.plat.generator.common.Module;
import io.github.kylinhunter.plat.generator.mybatis.MyPlusGeneratorConfig;
import io.github.kylinhunter.plat.generator.mybatis.MyPlusGeneratorCustomize;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 10:29
 **/
public class DefaultMyPlusGeneratorCustomize implements MyPlusGeneratorCustomize {
    @Override
    public void customize(Module module, MyPlusGeneratorConfig myPlusGeneratorConfig) {
        module.getEntityClassNames().forEach(System.out::println);
    }

}
