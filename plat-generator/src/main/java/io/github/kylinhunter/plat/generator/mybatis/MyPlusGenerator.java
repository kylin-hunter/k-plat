package io.github.kylinhunter.plat.generator.mybatis;

import java.io.File;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;

import io.github.kylinhunter.commons.exception.embed.ParamException;
import io.github.kylinhunter.commons.io.file.UserDirUtils;
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

    private void check() {
       
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
