package com.kylinhunter.plat.generator.web.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-01 16:00
 **/
@Data
@NoArgsConstructor
public abstract class ConfigurationsAdapter implements Configurations {
    protected ConfigurationBuilder configurationBuilder;
    private ConfigurationsCallback configurationsCallback;

    public ConfigurationsAdapter(@NonNull ConfigurationsCallback configurationsCallback) {
        this.configurationsCallback = configurationsCallback;
    }

    @Override
    public void init(ConfigurationBuilder configurationBuilder) {
        this.configurationBuilder = configurationBuilder;
        initTemplateConfig(configurationBuilder.getCodeContext().getTemplateConfig());
        initGlobalConfig(configurationBuilder.getCodeContext().getGlobalConfig());
        initPackageConfig(configurationBuilder.getCodeContext().getPackageConfig());
        initStrategyConfig(configurationBuilder.getCodeContext().getStrategyConfigs());
        configurationsCallback.configure(configurationBuilder.getCodeContext());
    }

    public void initGlobalConfig(GlobalConfig globalConfig) {
    }

    public void initPackageConfig(PackageConfig packageConfig) {
    }

    public void initTemplateConfig(TemplateConfig templateConfig) {
    }

    public void initStrategyConfig(StrategyConfigs strategyConfigs) {
    }

}
