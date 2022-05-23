package com.kylinhunter.plat.generator.mybatis;

import java.io.File;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-02 21:09
 **/
@Slf4j
@Data
@NoArgsConstructor
public class MyPlusGenerator {
    private Module module;
    private MyPlusGeneratorConfig myPlusGeneratorConfig;

    public void exec(boolean clear) {

        this.check();
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(myPlusGeneratorConfig.getGlobalConfig());
        autoGenerator.setDataSource(myPlusGeneratorConfig.getDataSourceConfig());
        autoGenerator.setPackageInfo(myPlusGeneratorConfig.getPackageConfig());
        autoGenerator.setStrategy(myPlusGeneratorConfig.getStrategyConfig());
        autoGenerator.setTemplate(myPlusGeneratorConfig.getTemplateConfig());
        if (clear) {
            this.clear(autoGenerator.getGlobalConfig());
        }
        autoGenerator.execute();
    }

    public Module createModule(String name) {
        this.module = new Module(name);
        return this.module;
    }

    private void check() {
        if (module == null) {
            throw new ParamException("module is null");
        }
        if (myPlusGeneratorConfig == null) {
            throw new ParamException("myPlusGeneratorConfig is null");
        }
    }

    public void clear(GlobalConfig globalConfig) {
        File outputDir = new File(globalConfig.getOutputDir());
        UserDirUtils.deleteQuietly(outputDir);
        log.info("delete outputDir=>" + outputDir.getAbsolutePath());
    }

}
