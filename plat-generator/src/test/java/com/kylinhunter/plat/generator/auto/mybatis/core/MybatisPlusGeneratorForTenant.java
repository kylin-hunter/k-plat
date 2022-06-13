package com.kylinhunter.plat.generator.auto.mybatis.core;

import com.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForTenant extends CoreMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kplat_tenant");
    }

    public static void main(String[] args) {
        new MybatisPlusGeneratorForTenant().exec();
    }

}