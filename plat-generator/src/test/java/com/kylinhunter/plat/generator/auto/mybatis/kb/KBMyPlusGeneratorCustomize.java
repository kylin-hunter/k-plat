package com.kylinhunter.plat.generator.auto.mybatis.kb;

import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.kylinhunter.plat.generator.common.Module;
import com.kylinhunter.plat.generator.mybatis.MyPlusGeneratorConfig;
import com.kylinhunter.plat.generator.mybatis.MyPlusGeneratorCustomize;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 10:29
 **/
public class KBMyPlusGeneratorCustomize implements MyPlusGeneratorCustomize {

    @Override
    public void customize(Module module, MyPlusGeneratorConfig myPlusGeneratorConfig) {
        module.getEntityClassNames().forEach(System.out::println);
        final StrategyConfig strategyConfig = myPlusGeneratorConfig.getStrategyConfig();
        strategyConfig.setTablePrefix("kb_");
        final PackageConfig packageConfig = myPlusGeneratorConfig.getPackageConfig();
        packageConfig.setParent("com.kylinhunter.plat.kb");
    }
}
