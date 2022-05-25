package com.kylinhunter.plat.generator.core.util;

import java.util.Arrays;

import com.kylinhunter.plat.generator.core.configuration.CodeContext;
import com.kylinhunter.plat.generator.core.configuration.StrategyConfigs;
import com.kylinhunter.plat.generator.core.configuration.Template;
import com.kylinhunter.plat.generator.core.configuration.TemplateType;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-01 17:25
 **/
public class CodeContextUtils {
    public static void addSkipField(CodeContext codeContext, TemplateType templateType, String skipField) {
        StrategyConfigs strategyConfigs = codeContext.getStrategyConfigs();
        Arrays.stream(Template.values()).filter(t -> t.getType() == templateType).forEach(t -> {
            strategyConfigs.getStrategyConfig(t).addSkipField(skipField);
        });
    }
}
