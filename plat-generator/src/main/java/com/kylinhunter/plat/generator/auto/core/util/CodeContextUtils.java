package com.kylinhunter.plat.generator.auto.core.util;

import java.util.Arrays;

import com.kylinhunter.plat.generator.auto.core.configuration.CodeContext;
import com.kylinhunter.plat.generator.auto.core.configuration.StrategyConfigs;
import com.kylinhunter.plat.generator.auto.core.configuration.Template;
import com.kylinhunter.plat.generator.auto.core.configuration.TemplateType;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 17:25
 **/
public class CodeContextUtils {
    public static void addSkipField(CodeContext codeContext, TemplateType templateType, String skipField) {
        StrategyConfigs strategyConfigs = codeContext.getStrategyConfigs();
        Arrays.stream(Template.values()).filter(t -> t.getType() == templateType)
                .forEach(t -> strategyConfigs.get(t).addSkipField(skipField));
    }
}
