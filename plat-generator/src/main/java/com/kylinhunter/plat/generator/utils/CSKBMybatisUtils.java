package com.kylinhunter.plat.generator.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.kylinhunter.plat.generator.mybatis.CodeGenerator;
import com.google.common.collect.Lists;
import com.kylinhunter.plat.commons.exception.inner.InternalException;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-01 10:29
 **/
public class CSKBMybatisUtils {

    private static final String SRC_JAVA = "src/main/java";
    private static final File SRC_DIR_CSKB_SERVER_CORE = UserDirUtils.getDir("cskb-server-core/" + SRC_JAVA);
    private static final File SRC_DIR_CSKB_API = UserDirUtils.getDir("cskb-api/" + SRC_JAVA);
    private static final File SRC_DIR_CSKB_DAO = UserDirUtils.getDir("cskb-dao/" + SRC_JAVA);

    public static final File OUTPUT_MYBATIS = UserDirUtils.getDir("cskb-generator/output/mybatis-generator");

    public static File getApiEntity(CodeGenerator.Module module, String name) {
        return new File(SRC_DIR_CSKB_API,
                "com/kylinhunter/plat/api/module/" + module.getName() + "/bean/entity/" + name + ".java");

    }

    public static List<File> getApiEntitys(CodeGenerator.Module module, String... names) {
        List<File> files = Lists.newArrayList();
        for (String name : names) {
            files.add(getApiEntity(module, name));

        }
        return files;
    }

    public static File getDaoMappper(CodeGenerator.Module module, String name) {
        return new File(SRC_DIR_CSKB_DAO, "com/kylinhunter/plat/dao/mapper/" + module.getName() + "/"
                + name + "Mapper.java");

    }

    public static List<File> getDaoMapppers(CodeGenerator.Module module, String... names) {
        List<File> files = Lists.newArrayList();
        for (String name : names) {
            files.add(getDaoMappper(module, name));

        }
        return files;
    }

    //    public static File getCoreFile(String name) {
    //        return new File(SRC_DIR_CSKB_SERVER_CORE, SRC_ENTITY + "/" + name);
    //
    //    }

    public static File getMybatisEntity(CodeGenerator.Module module, String name) {
        File file =
                new File(OUTPUT_MYBATIS,
                        "com/kylinhunter/plat/api/module/" + module.getName() + "/bean/entity/" + name + ".java");
        if (!file.exists()) {
            throw new InternalException("no file " + file.getAbsolutePath());
        }
        return file;
    }

    public static List<File> getMybatisEntitys(CodeGenerator.Module module, String... names) {
        List<File> files = Lists.newArrayList();
        for (String name : names) {
            files.add(getMybatisEntity(module, name));

        }
        return files;
    }

    public static File getMybatisMapper(CodeGenerator.Module module, String name) {
        File file = new File(OUTPUT_MYBATIS, "com/kylinhunter/plat/dao/mapper/" +
                module.getName() + "/" + name + "Mapper.java");
        if (!file.exists()) {
            throw new InternalException("no file " + file.getAbsolutePath());
        }
        return file;
    }

    public static List<File> getMybatisMappers(CodeGenerator.Module module, String... names) {
        List<File> files = Lists.newArrayList();
        for (String name : names) {
            files.add(getMybatisMapper(module, name));

        }
        return files;
    }

    public static void copyEntityFiles(CodeGenerator.Module module, String... names) throws IOException {
        List<File> srcEntitys = CSKBMybatisUtils.getMybatisEntitys(module, names);
        List<File> toEntitys = CSKBMybatisUtils.getApiEntitys(module, names);

        for (int i = 0; i < srcEntitys.size(); i++) {
            System.out.println(srcEntitys.get(i) + "\n>" + toEntitys.get(i));
            FileUtils.copyFile(srcEntitys.get(i), toEntitys.get(i));
        }
    }

    public static void copyMapperFiles(CodeGenerator.Module module, String... names) throws IOException {
        List<File> srcEntitys = CSKBMybatisUtils.getMybatisMappers(module, names);
        List<File> toEntitys = CSKBMybatisUtils.getDaoMapppers(module, names);

        for (int i = 0; i < srcEntitys.size(); i++) {
            System.out.println(srcEntitys.get(i) + "\n>" + toEntitys.get(i));
            FileUtils.copyFile(srcEntitys.get(i), toEntitys.get(i));
        }
    }
}
