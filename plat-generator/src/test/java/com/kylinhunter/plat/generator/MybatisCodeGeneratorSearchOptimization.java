package com.kylinhunter.plat.generator;

import java.io.IOException;

import com.kylinhunter.plat.commons.test.MainTestInvoker;
import com.kylinhunter.plat.generator.mybatis.CodeGenerator;
import com.kylinhunter.plat.generator.utils.CSKBMybatisUtils;

public class MybatisCodeGeneratorSearchOptimization {

    public static void run() throws IOException {
        CodeGenerator codeGenerator = new CodeGenerator();

        CodeGenerator.Module module = codeGenerator.createModule("search");
//        module.setTableNames("cskb_search_record");
//        module.setTableNames("cskb_dictionary");
//        module.setTableNames("cskb_search_records_hot");
        module.setTableNames("cskb_dictionary_group");


        codeGenerator.setModule(module);
        codeGenerator.exec(true);

//        CSKBMybatisUtils.copyEntityFiles(module, "SearchRecord");
//        CSKBMybatisUtils.copyMapperFiles(module, "SearchRecord");
//        CSKBMybatisUtils.copyEntityFiles(module, "SearchRecordsHot");
//        CSKBMybatisUtils.copyMapperFiles(module, "SearchRecordsHot");
        CSKBMybatisUtils.copyEntityFiles(module, "DictionaryGroup");
        CSKBMybatisUtils.copyMapperFiles(module, "DictionaryGroup");
    }

    public static void main(String[] args) {
        MainTestInvoker.run(() -> {
            run();
        });
    }

}