package io.github.kylinhunter.plat.generator.auto.kplat.core;

import io.github.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorFor4Config;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorFor4Config extends CoreKPlatCodeGennerator {

    @Override
    public Class<? extends DefaultMybatisPlusGenerator> getMybatisPlusGenerator() {
        return MybatisPlusGeneratorFor4Config.class;
    }

    public static void main(String[] args) {
        new KPlatCodeGenneratorFor4Config().exec(true);
    }
}
