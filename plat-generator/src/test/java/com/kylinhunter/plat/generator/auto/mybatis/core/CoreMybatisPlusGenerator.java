package com.kylinhunter.plat.generator.auto.mybatis.core;

import com.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-11 17:47
 **/
@Slf4j
public abstract class CoreMybatisPlusGenerator extends DefaultMybatisPlusGenerator {

    @Override
    public String getModuleName() {
        return "core";
    }
}
