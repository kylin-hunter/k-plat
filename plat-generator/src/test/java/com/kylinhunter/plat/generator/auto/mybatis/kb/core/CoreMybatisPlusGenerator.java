package com.kylinhunter.plat.generator.auto.mybatis.kb.core;

import com.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import com.kylinhunter.plat.generator.auto.mybatis.kb.KBMyPlusGeneratorCustomize;
import com.kylinhunter.plat.generator.common.Module;
import com.kylinhunter.plat.generator.mybatis.MyPlusGeneratorCustomize;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-11 17:47
 **/
@Slf4j
public abstract class CoreMybatisPlusGenerator extends DefaultMybatisPlusGenerator {

    @Override
    public Module getModule() {
        Module module = new Module("core");
        module.setTablePrefix("kb_");
        return module;
    }





    public  MyPlusGeneratorCustomize getMyPlusGeneratorCustomize(){
        return  new KBMyPlusGeneratorCustomize();
    }

}
