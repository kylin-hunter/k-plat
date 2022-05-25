package com.kylinhunter.plat.generator.core;

import com.kylinhunter.plat.generator.common.GenConst;
import com.kylinhunter.plat.generator.common.Module;
import com.kylinhunter.plat.generator.core.configuration.Configurations;
import com.kylinhunter.plat.generator.core.configuration.GlobalConfig;
import com.kylinhunter.plat.generator.core.configuration.StrategyConfigs;
import com.kylinhunter.plat.generator.core.configuration.Template;
import com.kylinhunter.plat.generator.core.configuration.TemplateConfig;
import com.kylinhunter.plat.generator.mybatis.MybatisPlusGeneratorForRole;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class CodeGenneratorForRole {

    public static void main(String[] args) throws InterruptedException {
        Module module = MybatisPlusGeneratorForRole.getConfig().getModule();

        module.loadEntityClasses();

        AutoCodeGennerator autoCodeGennerator = new AutoCodeGennerator();
        // core vo
        autoCodeGennerator.withConfigurations(configurations(module)).execute();
        // core service
        //        autoCodeGennerator.createConfig().withCodeContextBuilder(configurationsForService(module)).execute();
        // core controller
        //        autoCodeGennerator.createConfig().withCodeContextBuilder(configurationsForController(module)).execute();
    }

    public static Configurations configurations(Module module) {
        return new ConfigurationsForDefault((codeContext) -> {
            codeContext.getGlobalConfig().setModuleName(module.getName());
            codeContext.getStrategyConfigs().setEntityClasses(module.getEntityClasses());
        });
    }

    public static Configurations configurationsForService(Module module) {

        return new ConfigurationsForDefaultService((codeContext) -> {
            GlobalConfig globalConfig = codeContext.getGlobalConfig();
            StrategyConfigs strategyConfigs = codeContext.getStrategyConfigs();
            TemplateConfig templateConfig = codeContext.getTemplateConfig();

            globalConfig.setModuleName(module.getName());
            codeContext.getStrategyConfigs().setEntityClasses(module.getEntityClasses());

            templateConfig.setEnabled(Template.SERVICE_LOCAL, true);
            templateConfig.setEnabled(Template.SERVICE_LOCAL_IMP, true);
//            templateConfig.setEnabled(Template.SERVICE_RPC, true);
//            templateConfig.setEnabled(Template.SERVICE_RPC_IMP, true);

            globalConfig.setOutputDir(Template.SERVICE_LOCAL_IMP, GenConst.DEFAULT_OUTPUT.getAbsolutePath());
//            globalConfig.setOutputDir(Template.SERVICE_RPC_IMP, GenConst.DEFAULT_OUTPUT.getAbsolutePath());
        });
    }

    public static Configurations configurationsForController(Module module) {

        return new ConfigurationsForDefaultController((codeContext) -> {

            GlobalConfig globalConfig = codeContext.getGlobalConfig();
            StrategyConfigs strategyConfigs = codeContext.getStrategyConfigs();
            TemplateConfig templateConfig = codeContext.getTemplateConfig();

            globalConfig.setModuleName(module.getName());
            codeContext.getStrategyConfigs().setEntityClasses(module.getEntityClasses());

            templateConfig.setEnabled(Template.CONTROLLER, true);

        });
    }

}
