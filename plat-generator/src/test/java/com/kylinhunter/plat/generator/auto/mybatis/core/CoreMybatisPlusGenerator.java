package com.kylinhunter.plat.generator.auto.mybatis.core;

import com.kylinhunter.plat.generator.auto.mybatis.DefaultMyPlusGeneratorConfigHelper;
import com.kylinhunter.plat.generator.mybatis.MyPlusGenerator;
import com.kylinhunter.plat.generator.mybatis.MyPlusGeneratorConfig;
import com.kylinhunter.plat.generator.common.Module;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-11 17:47
 **/
public abstract class CoreMybatisPlusGenerator {
    public void exec() {
        MyPlusGenerator myPlusGenerator = new MyPlusGenerator();
        myPlusGenerator.setMyPlusGeneratorConfig(getConfig());
        myPlusGenerator.exec(true);
    }

    public MyPlusGeneratorConfig getConfig() {
        Module module = new Module("core");
        init(module);
        return DefaultMyPlusGeneratorConfigHelper.getConfig(module);
    }

    public abstract void init(Module module);
}
