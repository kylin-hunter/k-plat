package com.kylinhunter.plat.generator.mybatis;

import com.kylinhunter.plat.generator.common.Module;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:25
 **/
@FunctionalInterface
public interface MyPlusGeneratorCustomize {
    void customize(Module module, MyPlusGeneratorConfig myPlusGeneratorConfig);
}
