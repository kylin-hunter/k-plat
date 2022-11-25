package io.github.kylinhunter.plat.generator.kplat.configuration;

import io.github.kylinhunter.plat.generator.common.Module;

@FunctionalInterface
public interface ConfigurationsCustomize {
    void customize(Module module, Configurations configurations);
}
