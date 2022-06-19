package com.kylinhunter.plat.generator.kplat.configuration;

import com.kylinhunter.plat.generator.common.Module;

@FunctionalInterface
public interface ConfigurationsCustomize {
    void customize(Module module, Configurations configurations);
}
