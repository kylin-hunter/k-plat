package io.github.kylinhunter.plat.generator.kplat.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.kylinhunter.commons.io.file.UserDirUtils;

class PackageConfigTest {

    @BeforeAll
    static void beforeAll() {

    }

    @Test
    void getPackage() {
        GlobalConfig globalConfig = new GlobalConfig();

        globalConfig.setDefaultOutputDir(UserDirUtils.getTmpDir());

        PackageConfig packageConfig = new PackageConfig(globalConfig);
        packageConfig.setParentPattern("com.kylinhunter.plat.%s.test.");
        globalConfig.setModuleName("");

        packageConfig.setPackagePattern(Template.VO_CREATE, "module.%s.test");
        Assertions.assertEquals("com.kylinhunter.plat.test.module.test",
                packageConfig.getPackage(Template.VO_CREATE));
        globalConfig.setModuleName("m1");

        Assertions.assertEquals("com.kylinhunter.plat.m1.test.module.m1.test",
                packageConfig.getPackage(Template.VO_CREATE));
    }

    @Test
    void getPackagePath() {

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setDefaultOutputDir(UserDirUtils.getTmpDir());
        globalConfig.setModuleName("m1");

        PackageConfig packageConfig = new PackageConfig(globalConfig);
        packageConfig.setParentPattern("com.kylinhunter.plat.%s.test.");
        packageConfig.setPackagePattern(Template.VO_CREATE, "module.%s.test");

        Assertions.assertEquals(globalConfig.getOutputDir(Template.VO_CREATE).resolve(
                "com/kylinhunter/plat/m1/test/module/m1/test"),
                packageConfig.getPackagePath(Template.VO_CREATE));

    }
}