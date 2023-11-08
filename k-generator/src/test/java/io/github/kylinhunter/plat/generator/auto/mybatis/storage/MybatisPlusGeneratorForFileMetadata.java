package io.github.kylinhunter.plat.generator.auto.mybatis.storage;

import io.github.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForFileMetadata extends StorageMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kplat_file_metadata");
    }

    public static void main(String[] args) {
        new MybatisPlusGeneratorForFileMetadata().exec();
    }

}