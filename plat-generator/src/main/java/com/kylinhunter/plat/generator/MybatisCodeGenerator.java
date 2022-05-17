package com.kylinhunter.plat.generator;

import com.kylinhunter.plat.generator.mybatis.CodeGenerator;

public class MybatisCodeGenerator {
    public static void main(String[] args) {
        CodeGenerator codeGenerator = new CodeGenerator();

        CodeGenerator.Module module = codeGenerator.createModule("document");
        module.setTableNames("cskb_doc_draft", "cskb_doc_audit", "cskb_doc_pub");
        codeGenerator.setModule(module);
        codeGenerator.exec(true);

        module = codeGenerator.createModule("faq");
        module.setTableNames("cskb_faq_draft", "cskb_faq_audit", "cskb_faq_pub", "cskb_faq_answer_channel",
                "cskb_faq_extend_question", "cskb_faq_specific_answer", "cskb_faq_template");
        codeGenerator.setModule(module);
        codeGenerator.exec(false);

        module = codeGenerator.createModule("core");
        module.setTableNames("cskb_agent_config", "cskb_bpm_config");
        codeGenerator.setModule(module);
        codeGenerator.exec(false);




        module = codeGenerator.createModule("portal");
        module.setTableNames("cskb_comment", "cskb_my_favorites", "cskb_my_notes");
        codeGenerator.setModule(module);
        codeGenerator.exec(false);


        module = codeGenerator.createModule("compare");
        module.setTableNames("cskb_compare");
        codeGenerator.setModule(module);
        codeGenerator.exec(false);

    }

}