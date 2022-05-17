package com.kylinhunter.plat.generator.web;

import com.kylinhunter.plat.generator.web.configuration.ConfigurationBuilder;
import com.kylinhunter.plat.generator.web.configuration.Configurations;
import com.kylinhunter.plat.generator.web.engine.AbstractTemplateEngine;
import com.kylinhunter.plat.generator.web.engine.VelocityTemplateEngine;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-04 10:53
 **/
@Slf4j
@Data
public class AutoCodeGennerator {

    protected ConfigurationBuilder config = new ConfigurationBuilder();  /*配置信息*/
    private AbstractTemplateEngine templateEngine;  /*模板引擎*/

    public AutoCodeGennerator createConfig() {
        this.config = new ConfigurationBuilder();
        return this;
    }

    public AutoCodeGennerator withConfig(Configurations configurations) {
        configurations.init(this.config);
        return this;
    }

    /**
     * @return void
     * @throws
     * @title 生成代码
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 8:43 下午
     */
    public void execute() {
        log.debug("==========================准备生成文件...==========================");
        if (templateEngine == null) {
            templateEngine = new VelocityTemplateEngine(); /* 默认模板引擎采用 Velocity */
        }
        templateEngine.init(config.init()).mkdirs().batchOutput().open();
        log.debug("==========================文件生成完成！！！==========================");
    }

}
