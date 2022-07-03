package com.kylinhunter.plat.generator.auto.kplat.storage;

import com.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import com.kylinhunter.plat.generator.auto.mybatis.storage.MybatisPlusGeneratorForFileMetadata;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForFileMetadata extends StorageKPlatCodeGennerator {

    @Override
    public Class<? extends DefaultMybatisPlusGenerator> getMybatisPlusGenerator() {
        return MybatisPlusGeneratorForFileMetadata.class;
    }

    public static void main(String[] args) {
        new KPlatCodeGenneratorForFileMetadata().exec(false);
    }
}
