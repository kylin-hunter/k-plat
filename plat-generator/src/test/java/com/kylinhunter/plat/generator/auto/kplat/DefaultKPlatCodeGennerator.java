package com.kylinhunter.plat.generator.auto.kplat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.kylinhunter.plat.commons.exception.inner.GeneralException;
import com.kylinhunter.plat.commons.exception.inner.KIOException;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;
import com.kylinhunter.plat.generator.common.Module;
import com.kylinhunter.plat.generator.kplat.KPlatCodeGennerator;
import com.kylinhunter.plat.generator.kplat.configuration.Configurations;

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
        Module module = initModule();
        KPlatCodeGennerator kPlatCodeGennerator = new KPlatCodeGennerator();
        Configurations config = DefaultCodeGenneratorConfigHelper.getConfig(module);
        kPlatCodeGennerator.withConfigurations(config).execute();
        if (copy) {
            copy(config);
        }

    }

    public abstract Module initModule();

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

    private File getToApiDir(Configurations config, String prefix) {
        File apiSource = UserDirUtils.getDir("plat-api/src/main/java");
        String parentPackageRelativePath =
                config.getCodeContext().getPackageConfig().getParentPackageRelativePath();
        File to = new File(apiSource, parentPackageRelativePath + File.separator + prefix);
        return to;
    }

    private File getToModuleDir(Configurations config, String module) {
        File apiSource = UserDirUtils.getDir("plat-server-" + module + "/src/main/java");
        String parentPackageRelativePath =
                config.getCodeContext().getPackageConfig().getParentPackageRelativePath();
        File to = new File(apiSource, parentPackageRelativePath + File.separator + module);
        return to;
    }

    private void copyModule(Configurations config) throws IOException {
        copy(getFromDir(config, "core"), getToModuleDir(config, "core"));

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
