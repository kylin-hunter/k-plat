package com.kylinhunter.plat.generator.auto.mybatis.storage;

import com.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForFileRelation extends StorageMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kplat_file_relation");
    }

    public static void main(String[] args) {
        new MybatisPlusGeneratorForFileRelation().exec();
    }

}