package com.kylinhunter.plat.generator.auto.mybatis.knowledge;

import com.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import com.kylinhunter.plat.generator.common.Module;
import com.kylinhunter.plat.generator.mybatis.MyPlusGeneratorConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-11 17:47
 **/
@Slf4j
public abstract class KnowledgeMybatisPlusGenerator extends DefaultMybatisPlusGenerator {

    @Override
    public Module getModule() {
        Module module = new Module("kb");
        module.setTablePrefix("kb_");
        return module;
    }

    public MyPlusGeneratorConfig getConfig() {
        Module module = getModule();
        init(module);
        return KnowledgeMyPlusGeneratorConfigHelper.getConfig(module);
    }
}
