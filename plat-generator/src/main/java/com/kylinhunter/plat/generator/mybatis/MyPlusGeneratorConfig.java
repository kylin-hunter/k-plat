package com.kylinhunter.plat.generator.mybatis;

import java.io.File;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Lists;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;

import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:09
 **/
@Data
public class MyPlusGeneratorConfig {

    public final File OUTPUT_MYBATIS = UserDirUtils.getDir("plat-generator/src_gen");
    private DataSourceConfig dataSourceConfig;
    private GlobalConfig globalConfig;
    private PackageConfig packageConfig;
    private StrategyConfig strategyConfig;
    private TemplateConfig templateConfig;
    private File output;

    public MyPlusGeneratorConfig(Module module) {
        this.setOutput(OUTPUT_MYBATIS);

        this.setDataSourceConfig(getDataSourceConfig());
        this.setGlobalConfig(getGlobalConfig());
        this.setPackageConfig(getPackageConfig(module));
        this.setStrategyConfig(getStrategyConfig(module));
        this.setTemplateConfig(getTemplateConfig());

    }

    public GlobalConfig getGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(OUTPUT_MYBATIS.getAbsolutePath());
        globalConfig.setAuthor("biji'an");
        globalConfig.setOpen(false);
        globalConfig.setServiceName("%sService");  // 设置Service接口生成名称
        globalConfig.setSwagger2(true); // 实体属性 Swagger2 注解
        globalConfig.setFileOverride(true);
        return globalConfig;
    }

    public DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/kp?serverTimezone=Asia/Shanghai&useUnicode=true"
                + "&characterEncoding=utf8&useSSL=false");
        dataSourceConfig.setSchemaName("kp");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        return dataSourceConfig;

    }

    private static PackageConfig getPackageConfig(Module module) {

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("");
        packageConfig.setParent("com.kylinhunter.plat");
        packageConfig.setService("api.module." + module.getName() + ".service.local");
        packageConfig.setEntity("api.module." + module.getName() + ".bean.entity");
        packageConfig.setMapper(module.getName() + ".dao.mapper");
        packageConfig.setServiceImpl(module.getName() + ".service.local");
        packageConfig.setController(module.getName() + ".controller");

        return packageConfig;

    }

    private static StrategyConfig getStrategyConfig(Module module) {
        StrategyConfig strategyConfig = new StrategyConfig();

        strategyConfig.setInclude(module.getTableNames());
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setSuperEntityClass(BaseEntity.class);
        strategyConfig.setSuperEntityColumns("id", "sys_created", "sys_updated", "agent_id", "created_user_id",
                "created_user_name", "last_edit_user_id", "last_edit_user_name", "last_edit_time", "delete_flag",
                "op_lock");

        strategyConfig.setVersionFieldName("op_lock"); // 乐观锁
        //        strategyConfig.setSuperControllerClass("com.kylinhunter.plat.****.BaseController");
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setLogicDeleteFieldName("deleted"); // 逻辑删除(deleted表明)

        TableFill tableFillCreate = new TableFill("user", FieldFill.INSERT); // 创建时自动填充策略user数据库表
        TableFill tableFillUpdate = new TableFill("user", FieldFill.INSERT_UPDATE); // 修改时
        List tableFills = Lists.newArrayList(tableFillCreate, tableFillUpdate);
        strategyConfig.setTableFillList(tableFills);
        strategyConfig.setTablePrefix("cskb_");
        return strategyConfig;
    }

    public TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("/strategies/entity.java");
        // templateConfig.setService();
        // templateConfig.setTemplateController();
        templateConfig.setXml(null);
        return templateConfig;

    }
}
