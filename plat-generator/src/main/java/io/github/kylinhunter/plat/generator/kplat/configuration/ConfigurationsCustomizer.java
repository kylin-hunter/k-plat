package io.github.kylinhunter.plat.generator.kplat.configuration;

import io.github.kylinhunter.plat.generator.common.Module;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:25
 **/

public class ConfigurationsCustomizer {

    public static Configurations customize(Module module, ConfigurationsCustomize configurationsCustomize) {
        Configurations configurations = new Configurations(module);
        configurationsCustomize.customize(module, configurations);
        return configurations;
    }
}
