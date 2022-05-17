package com.kylinhunter.plat.generator.web.configuration;

@FunctionalInterface
public interface ConfigurationsCallback {
    void configure(CodeContext codeContext);
}
