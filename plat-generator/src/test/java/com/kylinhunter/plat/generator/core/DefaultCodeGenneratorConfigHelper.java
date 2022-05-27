package com.kylinhunter.plat.generator.core;

import com.kylinhunter.plat.generator.common.Module;
import com.kylinhunter.plat.generator.core.configuration.Configurations;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-27 17:40
 **/
public class DefaultCodeGenneratorConfigHelper {

    public static Configurations configurations(Module module) {
        module.loadEntityClasses();
        return new ConfigurationsForDefault((codeContext) -> {
            codeContext.getGlobalConfig().setModuleName(module.getName());
            codeContext.getStrategyConfigs().setEntityClasses(module.getEntityClasses());
        });
    }
}
