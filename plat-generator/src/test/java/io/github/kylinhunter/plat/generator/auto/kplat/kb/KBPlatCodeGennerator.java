package io.github.kylinhunter.plat.generator.auto.kplat.kb;

import java.io.File;

import io.github.kylinhunter.commons.io.file.UserDirUtils;
import io.github.kylinhunter.plat.generator.auto.kplat.DefaultKPlatCodeGennerator;
import io.github.kylinhunter.plat.generator.kplat.configuration.Configurations;
import io.github.kylinhunter.plat.generator.kplat.configuration.ConfigurationsCustomize;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-19 23:45
 **/
public abstract class KBPlatCodeGennerator extends DefaultKPlatCodeGennerator {
    @Override
    public ConfigurationsCustomize getConfigurationsCustomize() {
        return new KBConfigurationsCustomize();
    }

    @Override
    protected File getToApiDir(Configurations config, String prefix) {
        File apiSource = UserDirUtils.getDir("extend/kb-api/src/main/java");
        String parentPackageRelativePath =
                config.getCodeContext().getPackageConfig().getParentPackageRelativePath();
        File to = new File(apiSource, parentPackageRelativePath + File.separator + prefix);
        return to;
    }

    @Override
    protected File getToModuleDir(Configurations config, String module) {
        File apiSource = UserDirUtils.getDir("extend/kb-server-" + module + "/src/main/java");
        String parentPackageRelativePath =
                config.getCodeContext().getPackageConfig().getParentPackageRelativePath();
        File to = new File(apiSource, parentPackageRelativePath + File.separator + module);
        return to;
    }
}
