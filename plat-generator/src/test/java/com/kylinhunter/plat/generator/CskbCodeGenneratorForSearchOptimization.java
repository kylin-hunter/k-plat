package com.kylinhunter.plat.generator;

import java.util.List;

import com.kylinhunter.plat.commons.io.file.UserDirUtils;
import com.kylinhunter.plat.generator.web.AutoCodeGennerator;
import com.kylinhunter.plat.generator.web.ConfigurationsForCSKBController;
import com.kylinhunter.plat.generator.web.ConfigurationsForCSKBService;
import com.kylinhunter.plat.generator.web.ConfigurationsForCSKBVO;
import com.kylinhunter.plat.generator.web.configuration.Configurations;
import com.kylinhunter.plat.generator.web.configuration.GlobalConfig;
import com.kylinhunter.plat.generator.web.configuration.StrategyConfigs;
import com.kylinhunter.plat.generator.web.configuration.Template;
import com.kylinhunter.plat.generator.web.configuration.TemplateConfig;
import com.google.common.collect.Lists;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-04 10:53
 **/
public class CskbCodeGenneratorForSearchOptimization {
    private static final String MODULE_NAME = "search";
    private static final String MODULE_PROJECT_NAME = "cskb-server-search";
    private static final List<Class<?>> ENTITY_CLASSES =
            //            Lists.newArrayList(SearchRecord.class);
            //            Lists.newArrayList(Dictionary.class);
            //                        Lists.newArrayList(SearchRecordsHot.class);
            Lists.newArrayList();

    public static void main(String[] args) {

        AutoCodeGennerator autoCodeGennerator = new AutoCodeGennerator();
        // core vo
        autoCodeGennerator.createConfig().withConfig(configurationsForVO()).execute();
        // core service
        autoCodeGennerator.createConfig().withConfig(configurationsForService()).execute();
        // core controller
        autoCodeGennerator.createConfig().withConfig(configurationsForController()).execute();
    }

    public static Configurations configurationsForVO() {

        return new ConfigurationsForCSKBVO((codeContext) -> {
            codeContext.getGlobalConfig().setModuleName(MODULE_NAME);
            codeContext.getStrategyConfigs().setEntityClasses(ENTITY_CLASSES);
            TemplateConfig templateConfig = codeContext.getTemplateConfig();
            templateConfig.setEnabled(Template.VO_REQ_QUREY, true);
            templateConfig.setEnabled(Template.VO_CREATE, true);
            templateConfig.setEnabled(Template.VO_UPDATE, true);
            templateConfig.setEnabled(Template.VO_RESPONSE, true);
        });
    }

    public static Configurations configurationsForService() {

        return new ConfigurationsForCSKBService((codeContext) -> {
            GlobalConfig globalConfig = codeContext.getGlobalConfig();
            StrategyConfigs strategyConfigs = codeContext.getStrategyConfigs();
            TemplateConfig templateConfig = codeContext.getTemplateConfig();

            globalConfig.setModuleName(MODULE_NAME);
            codeContext.getStrategyConfigs().setEntityClasses(ENTITY_CLASSES);

            templateConfig.setEnabled(Template.SERVICE_LOCAL, true);
            templateConfig.setEnabled(Template.SERVICE_LOCAL_IMP, true);
            templateConfig.setEnabled(Template.SERVICE_RPC, true);
            templateConfig.setEnabled(Template.SERVICE_RPC_IMP, true);

            globalConfig.setOutputDir(Template.SERVICE_LOCAL_IMP,
                    UserDirUtils.getDir(MODULE_PROJECT_NAME + "/src/main/java"));
            globalConfig.setOutputDir(Template.SERVICE_RPC_IMP,
                    UserDirUtils.getDir(MODULE_PROJECT_NAME + "/src/main/java"));
        });
    }

    public static Configurations configurationsForController() {

        return new ConfigurationsForCSKBController((codeContext) -> {

            GlobalConfig globalConfig = codeContext.getGlobalConfig();
            StrategyConfigs strategyConfigs = codeContext.getStrategyConfigs();
            TemplateConfig templateConfig = codeContext.getTemplateConfig();

            globalConfig.setModuleName(MODULE_NAME);
            codeContext.getStrategyConfigs().setEntityClasses(ENTITY_CLASSES);

            templateConfig.setEnabled(Template.CONTROLLER, false);

        });
    }

}
