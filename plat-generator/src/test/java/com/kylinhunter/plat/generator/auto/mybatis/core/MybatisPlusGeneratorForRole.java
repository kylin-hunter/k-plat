package com.kylinhunter.plat.generator.auto.mybatis.core;

import com.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForRole extends CoreMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kplat_role");
    }

    public static void main(String[] args) {
        MybatisPlusGeneratorForRole mybatisPlusGeneratorForRole = new MybatisPlusGeneratorForRole();
        mybatisPlusGeneratorForRole.exec();
    }

}