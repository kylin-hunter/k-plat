package io.github.kylinhunter.plat.generator.mybatis;

import io.github.kylinhunter.plat.generator.common.Module;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:25
 **/

public class MyPlusGeneratorCustomizer {

    public static MyPlusGeneratorConfig customize(Module module, MyPlusGeneratorCustomize myPlusGeneratorCustomize) {
        MyPlusGeneratorConfig myPlusGeneratorConfig = new MyPlusGeneratorConfig(module);
        myPlusGeneratorCustomize.customize(module, myPlusGeneratorConfig);
        return myPlusGeneratorConfig;
    }
}
