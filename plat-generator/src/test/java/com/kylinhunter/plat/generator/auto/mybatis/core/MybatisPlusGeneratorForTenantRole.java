package com.kylinhunter.plat.generator.auto.mybatis.core;

import com.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForTenantRole extends CoreMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kplat_tenant_role");
    }

    public static void main(String[] args) {
        new MybatisPlusGeneratorForTenantRole().exec();

    }

}