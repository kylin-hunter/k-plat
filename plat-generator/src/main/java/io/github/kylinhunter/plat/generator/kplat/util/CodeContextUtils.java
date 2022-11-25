package io.github.kylinhunter.plat.generator.kplat.util;

import java.util.Arrays;

import io.github.kylinhunter.plat.generator.kplat.configuration.CodeContext;
import io.github.kylinhunter.plat.generator.kplat.configuration.StrategyConfigs;
import io.github.kylinhunter.plat.generator.kplat.configuration.Template;
import io.github.kylinhunter.plat.generator.kplat.configuration.TemplateType;

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
