package com.kylinhunter.plat.generator.auto.core;

import java.io.File;

import com.kylinhunter.plat.commons.io.file.UserDirUtils;
import com.kylinhunter.plat.generator.auto.core.configuration.GlobalConfig;
import com.kylinhunter.plat.generator.auto.core.configuration.Template;
import com.kylinhunter.plat.generator.common.Module;
import com.kylinhunter.plat.generator.auto.core.configuration.ConfigurationsCustomizer;
import com.kylinhunter.plat.generator.auto.core.configuration.Configurations;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-27 17:40
 **/
public class DefaultCodeGenneratorConfigHelper {

    public static Configurations getConfig(Module module) {
        return ConfigurationsCustomizer.customize(module, e -> {
            module.getEntityClassNames().forEach(System.out::println);
        });

    }
}
