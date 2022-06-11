package com.kylinhunter.plat.generator.kplat;

import com.kylinhunter.plat.generator.kplat.configuration.CodeContext;
import com.kylinhunter.plat.generator.kplat.configuration.CodeContextBuilder;
import com.kylinhunter.plat.generator.kplat.configuration.Configurations;
import com.kylinhunter.plat.generator.kplat.engine.AbstractTemplateEngine;
import com.kylinhunter.plat.generator.kplat.engine.VelocityTemplateEngine;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
@Slf4j
@Data
public class KPlatCodeGennerator {

    protected CodeContextBuilder codeContextBuilder;  /*配置信息*/
    private AbstractTemplateEngine templateEngine;  /*模板引擎*/

    public KPlatCodeGennerator withConfigurations(Configurations configurations) {
        this.codeContextBuilder = new CodeContextBuilder(configurations.getCodeContext());
        templateEngine = new VelocityTemplateEngine(); /* 默认模板引擎采用 Velocity */

        return this;
    }

    /**
     * @return void
     * @title 生成代码
     * @description
     * @author BiJi'an
     * @date 2021/8/4 8:43 下午
     */
    public void execute() {
        log.info("==========================准备生成文件...==========================");
        CodeContext build = codeContextBuilder.build();
        templateEngine.init(build).mkdirs().batchOutput().open();
        log.info("==========================文件生成完成！！！==========================");
    }

}
