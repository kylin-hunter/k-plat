package com.kylinhunter.plat.generator.auto.mybatis.kb.core;

import com.kylinhunter.plat.generator.common.Module;

public class MybatisPlusGeneratorForDoc extends CoreMybatisPlusGenerator {

    @Override
    public void init(Module module) {
        module.addTable("kb_doc");
    }

    public static void main(String[] args) {
        new MybatisPlusGeneratorForDoc().exec();
    }

}