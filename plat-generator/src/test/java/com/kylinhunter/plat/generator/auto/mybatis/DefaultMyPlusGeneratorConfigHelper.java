package com.kylinhunter.plat.generator.auto.mybatis;

import com.kylinhunter.plat.generator.common.Module;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 10:29
 **/
public class DefaultMyPlusGeneratorConfigHelper {

    public static MyPlusGeneratorConfig getConfig(Module module) {
        return MyPlusGeneratorCustomizer.customize(module, e -> {
            module.getEntityClassNames().forEach(System.out::println);
        });

    }

}
