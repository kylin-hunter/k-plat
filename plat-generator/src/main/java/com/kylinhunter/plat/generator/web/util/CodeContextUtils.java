package com.kylinhunter.plat.generator.web.util;

import java.util.Arrays;

import com.kylinhunter.plat.generator.web.configuration.CodeContext;
import com.kylinhunter.plat.generator.web.configuration.StrategyConfigs;
import com.kylinhunter.plat.generator.web.configuration.Template;
import com.kylinhunter.plat.generator.web.configuration.TemplateType;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-10 17:25
 **/
public class CodeContextUtils {
    public static void addSkipField(CodeContext codeContext, TemplateType templateType, String skipField) {
        StrategyConfigs strategyConfigs = codeContext.getStrategyConfigs();
        Arrays.stream(Template.values()).filter(t -> t.getType() == templateType).forEach(t -> {
            strategyConfigs.getStrategyConfig(t).addSkipField(skipField);
        });
    }
}
