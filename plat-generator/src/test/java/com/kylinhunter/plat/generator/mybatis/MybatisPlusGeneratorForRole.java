package com.kylinhunter.plat.generator.mybatis;

import com.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForRole {

    public static void main(String[] args) {
        MyPlusGenerator myPlusGenerator = new MyPlusGenerator();
        myPlusGenerator.setMyPlusGeneratorConfig(getConfig());
        myPlusGenerator.exec(true);
    }

    public static MyPlusGeneratorConfig getConfig() {
        Module module = new Module("core");
        module.addTable("kplat_role");
        return DefaultMyPlusGeneratorConfigHelper.getConfig(module);
    }

}