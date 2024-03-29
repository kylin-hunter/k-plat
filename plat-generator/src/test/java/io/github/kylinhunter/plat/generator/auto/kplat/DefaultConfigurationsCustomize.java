package io.github.kylinhunter.plat.generator.auto.kplat;

import io.github.kylinhunter.plat.generator.common.Module;
import io.github.kylinhunter.plat.generator.kplat.configuration.Configurations;
import io.github.kylinhunter.plat.generator.kplat.configuration.ConfigurationsCustomize;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-27 17:40
 **/
public class DefaultConfigurationsCustomize implements ConfigurationsCustomize {

    @Override
    public void customize(Module module, Configurations configurations) {
        module.getEntityClassNames().forEach(System.out::println);

    }
}
