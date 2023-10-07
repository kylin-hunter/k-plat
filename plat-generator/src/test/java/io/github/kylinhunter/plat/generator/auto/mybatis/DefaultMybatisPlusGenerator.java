package io.github.kylinhunter.plat.generator.auto.mybatis;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import io.github.kylinhunter.commons.classloader.ExClassLoaderUtil;
import io.github.kylinhunter.commons.compiler.KplatCompiler;
import io.github.kylinhunter.commons.exception.embed.SystemException;
import io.github.kylinhunter.commons.io.file.UserDirUtils;
import io.github.kylinhunter.plat.generator.common.Module;
import io.github.kylinhunter.plat.generator.mybatis.MyPlusGenerator;
import io.github.kylinhunter.plat.generator.mybatis.MyPlusGeneratorConfig;
import io.github.kylinhunter.plat.generator.mybatis.MyPlusGeneratorCustomize;
import io.github.kylinhunter.plat.generator.mybatis.MyPlusGeneratorCustomizer;
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
        MyPlusGeneratorCustomize myPlusGeneratorCustomize = getMyPlusGeneratorCustomize();
        MyPlusGeneratorConfig myPlusGeneratorConfig =
                MyPlusGeneratorCustomizer.customize(module, myPlusGeneratorCustomize);
        myPlusGeneratorConfig.init();
        return myPlusGeneratorConfig;
    }

    public abstract void init(Module module);

    public abstract Module getModule();

    public MyPlusGeneratorCustomize getMyPlusGeneratorCustomize() {
        return new DefaultMyPlusGeneratorCustomize();
    }

    private void compile(MyPlusGeneratorConfig config) {
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

        ExClassLoaderUtil.addClassPath(UserDirUtils.getTmpDir().toPath());
    }
}
