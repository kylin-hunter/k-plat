package com.kylinhunter.plat.generator.auto.kplat.kb;

import com.kylinhunter.plat.generator.common.Module;
import com.kylinhunter.plat.generator.kplat.configuration.Configurations;
import com.kylinhunter.plat.generator.kplat.configuration.ConfigurationsCustomize;
import com.kylinhunter.plat.generator.kplat.configuration.PackageConfig;
import com.kylinhunter.plat.generator.kplat.configuration.TemplateConfig;
import com.kylinhunter.plat.generator.kplat.configuration.TemplateType;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-27 17:40
 **/
public class KBConfigurationsCustomize implements ConfigurationsCustomize {

    @Override
    public void customize(Module module, Configurations configurations) {
        module.getEntityClassNames().forEach(System.out::println);
        PackageConfig packageConfig = configurations.getCodeContext().getPackageConfig();
        packageConfig.setParentPattern("com.kylinhunter.plat.kb");
        final TemplateConfig templateConfig = configurations.getCodeContext().getTemplateConfig();
        templateConfig.setEnabled(TemplateType.SERVICE_INTERCEPTOR, true);

    }
}
