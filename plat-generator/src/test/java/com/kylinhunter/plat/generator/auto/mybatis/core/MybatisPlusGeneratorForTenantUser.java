package com.kylinhunter.plat.generator.auto.mybatis.core;

import com.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForTenantUser extends CoreMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kplat_tenant_user");
    }

    public static void main(String[] args) {
        new MybatisPlusGeneratorForTenantUser().exec();

    }

}