package com.kylinhunter.plat.generator.auto.mybatis;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.kylinhunter.plat.commons.classloader.KPlatClassLoaderUtil;
import com.kylinhunter.plat.commons.compiler.KplatCompiler;
import com.kylinhunter.plat.commons.exception.inner.SystemException;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;
import com.kylinhunter.plat.generator.common.Module;
import com.kylinhunter.plat.generator.mybatis.MyPlusGenerator;
import com.kylinhunter.plat.generator.mybatis.MyPlusGeneratorConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-11 17:47
 **/
@Slf4j
public abstract class DefaultMybatisPlusGenerator {
    public void exec() {
        exec(true, true);
    }

    public void exec(boolean clear, boolean compile) {
        MyPlusGenerator myPlusGenerator = new MyPlusGenerator();
        MyPlusGeneratorConfig config = getConfig();
        myPlusGenerator.setMyPlusGeneratorConfig(config);
        myPlusGenerator.exec(clear);
        if (compile) {
            compile(config);
        }
    }

    public MyPlusGeneratorConfig getConfig() {
        Module module = getModule();
        init(module);
        return DefaultMyPlusGeneratorConfigHelper.getConfig(module);
    }

    public abstract void init(Module module);

    public abstract Module getModule();

    private void compile(MyPlusGeneratorConfig config) {
        try {
            KplatCompiler kplatCompiler = new KplatCompiler();

            File output = config.getOutput();

            Collection<File> javaFiles = FileUtils.listFiles(output, new String[] {"java"}, true);

            javaFiles.forEach(e -> {
                log.info("compile file:" + e.getAbsolutePath()
                );
            });

            kplatCompiler.addSources(javaFiles);
            File compileOutput = UserDirUtils.getTmpDir(config.getModule().getName());
            kplatCompiler.setOutput(compileOutput);
            kplatCompiler.compile();
            KPlatClassLoaderUtil.addClassPath(UserDirUtils.getTmpDir().toPath(), true);
        } catch (IOException e) {
            throw new SystemException("output error", e);
        }
    }
}
