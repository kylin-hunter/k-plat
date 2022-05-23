package com.kylinhunter.plat.generator.mybatis;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:25
 **/

public class MyPlusGeneratorCustomizer {
    public static MyPlusGeneratorConfig customize(Module module, MyPlusGeneratorCustomize myPlusGeneratorCustomize) {
        MyPlusGeneratorConfig myPlusGeneratorConfig = new MyPlusGeneratorConfig(module);
        myPlusGeneratorCustomize.customize(myPlusGeneratorConfig);
        return myPlusGeneratorConfig;
    }
}
