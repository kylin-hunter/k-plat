package com.kylinhunter.plat.generator.mybatis;

public class MybatisPlusGeneratorForRole {

    public static void main(String[] args) {
        MyPlusGenerator myPlusGenerator = new MyPlusGenerator();

        Module module = myPlusGenerator.createModule("core");
        module.setTableNames("kp_role");

        myPlusGenerator.setMyPlusGeneratorConfig(DefaultMyPlusGeneratorConfigHelper.create(module));

        myPlusGenerator.exec(true);
    }

}