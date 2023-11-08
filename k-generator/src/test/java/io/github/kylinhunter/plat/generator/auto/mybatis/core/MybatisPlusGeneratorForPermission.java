package io.github.kylinhunter.plat.generator.auto.mybatis.core;

import io.github.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForPermission extends CoreMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kplat_permission");
    }

    public static void main(String[] args) {
        new MybatisPlusGeneratorForPermission().exec();
    }

}