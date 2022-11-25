package io.github.kylinhunter.plat.generator.auto.kplat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import io.github.kylinhunter.commons.exception.embed.GeneralException;
import io.github.kylinhunter.commons.exception.embed.InitException;
import io.github.kylinhunter.commons.exception.embed.KIOException;
import io.github.kylinhunter.commons.io.file.UserDirUtils;
import io.github.kylinhunter.plat.generator.auto.mybatis.DefaultMybatisPlusGenerator;
import io.github.kylinhunter.plat.generator.common.Module;
import io.github.kylinhunter.plat.generator.kplat.KPlatCodeGennerator;
import io.github.kylinhunter.plat.generator.kplat.configuration.Configurations;
import io.github.kylinhunter.plat.generator.kplat.configuration.ConfigurationsCustomize;
import io.github.kylinhunter.plat.generator.kplat.configuration.ConfigurationsCustomizer;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-11 18:10
 **/
public abstract class DefaultKPlatCodeGennerator {
    public void exec() {
        exec(true);
    }

    public void exec(boolean copy) {
        Module module = getModule();
        KPlatCodeGennerator kPlatCodeGennerator = new KPlatCodeGennerator();

        Configurations configurations =
                ConfigurationsCustomizer.customize(module, getConfigurationsCustomize());
        kPlatCodeGennerator.withConfigurations(configurations).execute();
        if (copy) {
            copy(configurations);
        }

    }

    public Module getModule() {
        try {
            return getMybatisPlusGenerator().newInstance().getConfig().getModule();
        } catch (Exception e) {
            throw new InitException("init module error", e);
        }
    }

    public abstract Class<? extends DefaultMybatisPlusGenerator> getMybatisPlusGenerator();

    public ConfigurationsCustomize getConfigurationsCustomize() {
        return new DefaultConfigurationsCustomize();
    }

    private void copy(Configurations config) {
        try {
            copyApi(config);
            copyModule(config);
        } catch (IOException e) {
            throw new GeneralException("copy error", e);
        }

    }

    private void copyApi(Configurations config) throws IOException {
        copy(getFromDir(config, "api"), getToApiDir(config, "api"));

    }

    private File getFromDir(Configurations config, String prefix) {
        File defaultOutputDir = config.getCodeContext().getGlobalConfig().getDefaultOutputDir().toFile();
        String parentPackageRelativePath =
                config.getCodeContext().getPackageConfig().getParentPackageRelativePath();
        File from = new File(defaultOutputDir, parentPackageRelativePath + File.separator + prefix);
        return from;
    }

    protected File getToApiDir(Configurations config, String prefix) {
        File apiSource = UserDirUtils.getDir("plat-api/src/main/java");
        String parentPackageRelativePath =
                config.getCodeContext().getPackageConfig().getParentPackageRelativePath();
        File to = new File(apiSource, parentPackageRelativePath + File.separator + prefix);
        return to;
    }

    protected File getToModuleDir(Configurations config, String module) {
        File apiSource = UserDirUtils.getDir("plat-server-" + module + "/src/main/java");
        String parentPackageRelativePath =
                config.getCodeContext().getPackageConfig().getParentPackageRelativePath();
        File to = new File(apiSource, parentPackageRelativePath + File.separator + module);
        return to;
    }

    private void copyModule(Configurations config) throws IOException {
        Module module = getModule();
        copy(getFromDir(config, module.getName()), getToModuleDir(config, module.getName()));

    }

    public void copy(File from, File to) throws IOException {
        if (from.exists() && to.exists()) {
            System.out.println("copy from " + from.getAbsolutePath());
            System.out.println("copy to   " + to.getAbsolutePath());
            FileUtils.copyDirectory(from, to);
        } else {
            throw new KIOException("dir no exist" + from.getAbsolutePath() + ":" + to.getAbsolutePath());
        }
    }
}
