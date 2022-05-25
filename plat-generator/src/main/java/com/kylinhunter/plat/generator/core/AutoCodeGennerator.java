package com.kylinhunter.plat.generator.core;

import com.kylinhunter.plat.generator.core.configuration.CodeContextBuilder;
import com.kylinhunter.plat.generator.core.configuration.Configurations;
import com.kylinhunter.plat.generator.core.engine.AbstractTemplateEngine;
import com.kylinhunter.plat.generator.core.engine.VelocityTemplateEngine;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
@Slf4j
@Data
public class AutoCodeGennerator {

    protected CodeContextBuilder codeContextBuilder;  /*配置信息*/
    private AbstractTemplateEngine templateEngine;  /*模板引擎*/

    public AutoCodeGennerator withConfigurations(Configurations configurations) {
        this.codeContextBuilder = new CodeContextBuilder();
        configurations.configure(this.codeContextBuilder);
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
        templateEngine.init(codeContextBuilder.build()).mkdirs().batchOutput().open();
        log.debug("==========================文件生成完成！！！==========================");
    }

}
