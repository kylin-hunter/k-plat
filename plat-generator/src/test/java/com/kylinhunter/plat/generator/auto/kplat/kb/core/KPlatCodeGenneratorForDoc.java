package com.kylinhunter.plat.generator.auto.kplat.kb.core;

import com.kylinhunter.plat.generator.auto.kplat.kb.KBPlatCodeGennerator;
import com.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import com.kylinhunter.plat.generator.auto.mybatis.kb.core.MybatisPlusGeneratorForDoc;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForDoc extends KBPlatCodeGennerator {

    @Override
    public Class<? extends DefaultMybatisPlusGenerator> getMybatisPlusGenerator() {
        return MybatisPlusGeneratorForDoc.class;
    }

    public static void main(String[] args) {
        new KPlatCodeGenneratorForDoc().exec(true);
    }
}
