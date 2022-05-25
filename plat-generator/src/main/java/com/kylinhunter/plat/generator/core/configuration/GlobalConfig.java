package com.kylinhunter.plat.generator.core.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.google.common.collect.Maps;
import com.kylinhunter.plat.commons.exception.inner.InternalException;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description 全局配置
 * @author BiJi'an
 * @date   2022/01/01
 **/
@Data
@Accessors(chain = true)
public class GlobalConfig {

    private String author;/* 开发人员*/

    private boolean swagger2 = false;  /*开启 swagger2 模式*/

    private boolean clearBeforExec = false; /*执行前对 outputDir下的自定义子目录 清空 */

    private Path defaultOutputDir = UserDirUtils.getTmpDir("auto_code", true).toPath(); /*生成文件的输出目录*/

    private boolean fileOverride = false;  /*是否覆盖已有文件*/

    private boolean autoCreateOutputDir = false;  /*是否自动创建outputDir*/

    private boolean open = true; /*是否打开输出目录*/
    private String moduleName = ""; /*模块名*/

    private Map<Template, Path> outputDirs = Maps.newHashMap();

    /**
     * @param outputDir
     * @return void
     * @throws
     * @title setOutputDir
     * @description
     * @author BiJi'an
     * @updateTime 2022/01/01 3:45 下午
     */
    public void setDefaultOutputDir(File outputDir) {
        this.setDefaultOutputDir(outputDir.toPath());
    }

    /**
     * @param outputDir
     * @return void
     * @throws
     * @title setOutputDir
     * @description
     * @author BiJi'an
     * @updateTime 2022/01/01 3:45 下午
     */
    public void setDefaultOutputDir(String outputDir) {
        this.setDefaultOutputDir(Paths.get(outputDir));
    }

    /**
     * @param outputDir
     * @return void
     * @throws
     * @title setOutputDir
     * @description
     * @author BiJi'an
     * @updateTime 2022/01/01 3:45 下午
     */
    public void setDefaultOutputDir(Path outputDir) {
        if (Files.exists(outputDir)) {
            if (!Files.isDirectory(outputDir)) {
                throw new InternalException("invalid outputDir" + outputDir);
            }
        } else {
            if (autoCreateOutputDir) {
                try {
                    Files.createDirectories(outputDir);
                } catch (IOException e) {
                    throw new InternalException("create outputDir error" + outputDir, e);
                }
            } else {
                throw new InternalException(" outputDir no exist " + outputDir);
            }
        }
        this.defaultOutputDir = outputDir;
    }

    public void setOutputDir(Template template, File outputDir) {
        this.outputDirs.put(template, outputDir.toPath());
    }

    public void setOutputDir(Template template, String outputDir) {
        this.outputDirs.put(template, Paths.get(outputDir));
    }

    public void setOutputDir(Template template, Path outputDir) {
        this.outputDirs.put(template, outputDir);
    }

    public Path getOutputDir(Template template) {
        return this.outputDirs.getOrDefault(template, defaultOutputDir);
    }

}
