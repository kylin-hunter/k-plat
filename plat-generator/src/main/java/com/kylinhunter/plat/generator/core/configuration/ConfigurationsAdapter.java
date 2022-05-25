package com.kylinhunter.plat.generator.core.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 16:00
 **/
@Data
@NoArgsConstructor
public abstract class ConfigurationsAdapter implements Configurations {
    protected CodeContextBuilder codeContextBuilder;
    private ConfigurationsCustomize configurationsCustomize;

    public ConfigurationsAdapter(@NonNull ConfigurationsCustomize configurationsCustomize) {
        this.configurationsCustomize = configurationsCustomize;
    }

    @Override
    public void configure(CodeContextBuilder codeContextBuilder) {
        this.codeContextBuilder = codeContextBuilder;
        init(codeContextBuilder.getCodeContext());
        configurationsCustomize.configure(codeContextBuilder.getCodeContext());
    }

    public abstract void init(CodeContext codeContext);
}
