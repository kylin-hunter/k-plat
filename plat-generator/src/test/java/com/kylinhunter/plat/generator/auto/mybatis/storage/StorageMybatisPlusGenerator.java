package com.kylinhunter.plat.generator.auto.mybatis.storage;

import com.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import com.kylinhunter.plat.generator.common.Module;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-11 17:47
 **/
@Slf4j
public abstract class StorageMybatisPlusGenerator extends DefaultMybatisPlusGenerator {

    @Override
    public Module getModule() {
        Module module = new Module("storage");
        module.setTablePrefix("kplat_");
        return module;
    }
}