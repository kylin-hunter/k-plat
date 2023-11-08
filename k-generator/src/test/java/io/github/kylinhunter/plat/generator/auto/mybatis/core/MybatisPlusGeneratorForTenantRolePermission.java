package io.github.kylinhunter.plat.generator.auto.mybatis.core;

import io.github.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForTenantRolePermission extends CoreMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kplat_tenant_role_permission");
    }

    public static void main(String[] args) {
        new MybatisPlusGeneratorForTenantRolePermission().exec();
    }

}