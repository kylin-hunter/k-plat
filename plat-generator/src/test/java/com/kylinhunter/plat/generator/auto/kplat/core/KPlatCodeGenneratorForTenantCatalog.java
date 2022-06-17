package com.kylinhunter.plat.generator.auto.kplat.core;

import com.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import com.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForTenantCatalog;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForTenantCatalog extends CoreKPlatCodeGennerator {

    @Override
    public Class<? extends DefaultMybatisPlusGenerator> getMybatisPlusGenerator() {
        return MybatisPlusGeneratorForTenantCatalog.class;
    }

    public static void main(String[] args) {
        new KPlatCodeGenneratorForTenantCatalog().exec(true);
    }
}
