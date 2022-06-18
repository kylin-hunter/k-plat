package com.kylinhunter.plat.generator.auto.kplat.knowledge;

import com.kylinhunter.plat.generator.auto.kplat.core.CoreKPlatCodeGennerator;
import com.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import com.kylinhunter.plat.generator.auto.mybatis.knowledge.MybatisPlusGeneratorForDoc;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForDoc extends CoreKPlatCodeGennerator {

    @Override
    public Class<? extends DefaultMybatisPlusGenerator> getMybatisPlusGenerator() {
        return MybatisPlusGeneratorForDoc.class;
    }

    public static void main(String[] args) {
        new KPlatCodeGenneratorForDoc().exec(false);
    }
}
