package com.kylinhunter.plat.generator.auto.mybatis.core;

import com.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorFor4Config extends CoreMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kplat_sys_config");
        module.addTable("kplat_sys_user_config");
        module.addTable("kplat_tenant_config");
        module.addTable("kplat_tenant_user_config");
    }

    public static void main(String[] args) {
        new MybatisPlusGeneratorFor4Config().exec();
    }

}