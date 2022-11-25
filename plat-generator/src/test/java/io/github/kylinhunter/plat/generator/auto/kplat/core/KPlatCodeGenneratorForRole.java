package io.github.kylinhunter.plat.generator.auto.kplat.core;

import io.github.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForRole;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForRole extends CoreKPlatCodeGennerator {

    @Override
    public Class<? extends DefaultMybatisPlusGenerator> getMybatisPlusGenerator() {
        return MybatisPlusGeneratorForRole.class;
    }

    public static void main(String[] args) {
        new KPlatCodeGenneratorForRole().exec();
    }
}
