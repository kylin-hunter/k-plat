package io.github.kylinhunter.plat.generator.auto.kplat.core;

import io.github.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForTenantRole;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForTenantRole extends CoreKPlatCodeGennerator {

    @Override
    public Class<? extends DefaultMybatisPlusGenerator> getMybatisPlusGenerator() {
        return MybatisPlusGeneratorForTenantRole.class;
    }

    public static void main(String[] args) {
        new KPlatCodeGenneratorForTenantRole().exec(true);
    }
}
