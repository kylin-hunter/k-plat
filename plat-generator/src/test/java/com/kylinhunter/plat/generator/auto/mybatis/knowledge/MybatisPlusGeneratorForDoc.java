package com.kylinhunter.plat.generator.auto.mybatis.knowledge;

import com.kylinhunter.plat.generator.auto.mybatis.core.CoreMybatisPlusGenerator;
import com.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForDoc extends KnowledgeMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kb_doc");
    }

    public static void main(String[] args) {
        new MybatisPlusGeneratorForDoc().exec();
    }

}