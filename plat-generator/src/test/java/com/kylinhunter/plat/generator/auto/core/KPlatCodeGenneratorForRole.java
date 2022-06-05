package com.kylinhunter.plat.generator.auto.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.kylinhunter.plat.commons.io.file.UserDirUtils;
import com.kylinhunter.plat.generator.auto.core.configuration.Configurations;
import com.kylinhunter.plat.generator.auto.mybatis.MybatisPlusGeneratorForRole;
import com.kylinhunter.plat.generator.common.GenConst;
import com.kylinhunter.plat.generator.common.Module;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForRole {

    public static void exec() {
        Module module = MybatisPlusGeneratorForRole.getConfig().getModule();
        KPlatCodeGennerator kPlatCodeGennerator = new KPlatCodeGennerator();
        Configurations config = DefaultCodeGenneratorConfigHelper.getConfig(module);
        kPlatCodeGennerator.withConfigurations(config).execute();

    }

    public static void copy() throws IOException {
        copyApi();
        copyModule();

    }

    public static void copyApi() throws IOException {
        final File defaultOutput = GenConst.DEFAULT_OUTPUT;

        File apiDir = UserDirUtils.getDir("plat-api/src/main/java");
        File from = new File(defaultOutput, "com/kylinhunter/plat/api");
        File to = new File(apiDir, "com/kylinhunter/plat/api");


        if(from.exists()&&to.exists()){
            System.out.println("copy from " + from.getAbsolutePath());
            System.out.println("copy from " + to.getAbsolutePath());
            FileUtils.copyDirectory(from,to);

        }


    }


    public static void copyModule() throws IOException {
        final File defaultOutput = GenConst.DEFAULT_OUTPUT;

        File apiDir = UserDirUtils.getDir("plat-server-core/src/main/java");
        File from = new File(defaultOutput, "com/kylinhunter/plat/core");
        File to = new File(apiDir, "com/kylinhunter/plat/core");


        if(from.exists()&&to.exists()){
            System.out.println("copy from " + from.getAbsolutePath());
            System.out.println("copy from " + to.getAbsolutePath());
            FileUtils.copyDirectory(from,to);

        }

    }

}
