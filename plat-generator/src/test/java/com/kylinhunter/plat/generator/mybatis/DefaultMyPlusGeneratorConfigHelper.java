package com.kylinhunter.plat.generator.mybatis;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 10:29
 **/
public class DefaultMyPlusGeneratorConfigHelper {

    public static MyPlusGeneratorConfig create(Module module) {

        return MyPlusGeneratorCustomizer.customize(module, (e -> {

        }));

    }

}
