package com.kylinhunter.plat.generator.auto.kplat.storage;

import com.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import com.kylinhunter.plat.generator.auto.mybatis.storage.MybatisPlusGeneratorForFileMetadata;
import com.kylinhunter.plat.generator.auto.mybatis.storage.MybatisPlusGeneratorForFileRelation;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForFileRelation extends StorageKPlatCodeGennerator {

    @Override
    public Class<? extends DefaultMybatisPlusGenerator> getMybatisPlusGenerator() {
        return MybatisPlusGeneratorForFileRelation.class;
    }

    public static void main(String[] args) {
        new KPlatCodeGenneratorForFileRelation().exec(true);
    }
}
