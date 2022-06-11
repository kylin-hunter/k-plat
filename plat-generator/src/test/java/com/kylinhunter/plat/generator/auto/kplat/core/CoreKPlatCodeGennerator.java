package com.kylinhunter.plat.generator.auto.kplat.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.kylinhunter.plat.commons.exception.inner.GeneralException;
import com.kylinhunter.plat.commons.exception.inner.KIOException;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;
import com.kylinhunter.plat.generator.auto.kplat.DefaultCodeGenneratorConfigHelper;
import com.kylinhunter.plat.generator.common.GenConst;
import com.kylinhunter.plat.generator.common.Module;
import com.kylinhunter.plat.generator.kplat.KPlatCodeGennerator;
import com.kylinhunter.plat.generator.kplat.configuration.Configurations;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-11 18:10
 **/
public abstract class CoreKPlatCodeGennerator {

    public void exec() {
        Module module = initModule();
        KPlatCodeGennerator kPlatCodeGennerator = new KPlatCodeGennerator();
        Configurations config = DefaultCodeGenneratorConfigHelper.getConfig(module);
        kPlatCodeGennerator.withConfigurations(config).execute();

    }

    public abstract Module initModule();

    public void copy() {
        try {
            copyApi();
            copyModule();
        } catch (IOException e) {
            throw new GeneralException("copy error", e);
        }

    }

    public void copyApi() throws IOException {
        File defaultOutput = GenConst.DEFAULT_OUTPUT;
        File from = new File(defaultOutput, "com/kylinhunter/plat/api");

        File apiDir = UserDirUtils.getDir("plat-api/src/main/java");
        File to = new File(apiDir, "com/kylinhunter/plat/api");
        copy(from, to);

    }

    public void copyModule() throws IOException {
        File defaultOutput = GenConst.DEFAULT_OUTPUT;
        File from = new File(defaultOutput, "com/kylinhunter/plat/core");

        File serverDir = UserDirUtils.getDir("plat-server-core/src/main/java");
        File to = new File(serverDir, "com/kylinhunter/plat/core");
        copy(from, to);
    }

    public void copy(File from, File to) throws IOException {
        if (from.exists() && to.exists()) {
            System.out.println("copy from " + from.getAbsolutePath());
            System.out.println("copy from " + to.getAbsolutePath());
            FileUtils.copyDirectory(from, to);
        } else {
            throw new KIOException("dir no exist");
        }
    }
}
