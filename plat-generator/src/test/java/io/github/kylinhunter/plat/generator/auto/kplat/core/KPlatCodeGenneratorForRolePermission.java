package io.github.kylinhunter.plat.generator.auto.kplat.core;

import io.github.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForRole;
import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForRolePermission;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForRolePermission extends CoreKPlatCodeGennerator {

    @Override
    public Class<? extends DefaultMybatisPlusGenerator> getMybatisPlusGenerator() {
        return MybatisPlusGeneratorForRolePermission.class;
    }

    public static void main(String[] args) {
        new KPlatCodeGenneratorForRolePermission().exec();
    }
}
