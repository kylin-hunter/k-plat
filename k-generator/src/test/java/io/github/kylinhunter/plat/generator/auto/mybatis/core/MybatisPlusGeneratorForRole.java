package io.github.kylinhunter.plat.generator.auto.mybatis.core;

import io.github.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForRole extends CoreMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kplat_role");
    }

    public static void main(String[] args) {
        new MybatisPlusGeneratorForRole().exec();
    }

}