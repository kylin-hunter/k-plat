package com.kylinhunter.plat.generator.auto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import com.kylinhunter.plat.commons.classloader.KPlatClassLoader;
import com.kylinhunter.plat.commons.compiler.KplatCompiler;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;
import com.kylinhunter.plat.generator.auto.kplat.core.CoreKPlatCodeGennerator;
import com.kylinhunter.plat.generator.auto.kplat.core.KPlatCodeGenneratorForTenantUser;
import com.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForTenantUser;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 17:27
 **/
@Slf4j
public class CodeGenerateForTenantUser {

    public static void main(String[] args) throws Exception {

        new MybatisPlusGeneratorForTenantUser().exec();

        compile();

        URL u = UserDirUtils.getTmpDir().toURI().toURL();
        URL[] urls = {u};
        KPlatClassLoader kPlatClassLoader = new KPlatClassLoader(urls);
        kPlatClassLoader.addPath(UserDirUtils.getTmpDir().toPath());

        log.info(Arrays.toString(kPlatClassLoader.getURLs()));

        kPlatClassLoader.loadClass("com.kylinhunter.plat.generator.auto.CodeGenerateForTenantUser");
        kPlatClassLoader.loadClass("com.kylinhunter.plat.api.module.core.bean.entity.TenantUser");
        CoreKPlatCodeGennerator coreKPlatCodeGennerator = new KPlatCodeGenneratorForTenantUser();
        coreKPlatCodeGennerator.exec();

    }

    private static void compile() throws IOException {
        KplatCompiler kplatCompiler = new KplatCompiler();

        File source1 = UserDirUtils.getFile("plat-generator/src_gen/com/kylinhunter/plat/core/dao/mapper"
                + "/TenantUserMapper.java");

        File source2 = UserDirUtils.getFile("plat-generator/src_gen/com/kylinhunter/plat/api/module/core/bean/entity"
                + "/TenantUser.java");



        File compile = UserDirUtils.getTmpDir();

        kplatCompiler.getSources().add(source1);
        kplatCompiler.getSources().add(source2);
        kplatCompiler.setCompile(compile);

        kplatCompiler.compile();
    }
}
