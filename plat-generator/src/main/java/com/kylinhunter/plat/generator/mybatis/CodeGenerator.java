package com.kylinhunter.plat.generator.mybatis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.generator.utils.CSKBMybatisUtils;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Lists;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-02 21:09
 **/
@Slf4j
@Data
@NoArgsConstructor
public class CodeGenerator {
    private Module module;

    /**
     * @param autoGenerator
     * @return com.baomidou.mybatisplus.generator.config.GlobalConfig
     * @throws
     * @title 全局配置
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/3 1:07 上午
     */
    private GlobalConfig initGlobalConfig(AutoGenerator autoGenerator) {

        GlobalConfig globalConfig = new GlobalConfig();
        //        globalConfig.setOutputDir(UserDirUtils.getUserDirTmpJava().getAbsolutePath());
        globalConfig.setOutputDir(CSKBMybatisUtils.OUTPUT_MYBATIS.getAbsolutePath());
        globalConfig.setAuthor("biji'an");
        globalConfig.setOpen(false);
        globalConfig.setServiceName("%sService");  // 设置Service接口生成名称
        globalConfig.setSwagger2(true); // 实体属性 Swagger2 注解
        globalConfig.setFileOverride(true);
        autoGenerator.setGlobalConfig(globalConfig);
        return globalConfig;
    }

    /**
     * @param autoGenerator
     * @return com.baomidou.mybatisplus.generator.config.DataSourceConfig
     * @throws
     * @title 数据源配置
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/3 1:17 上午
     */

    protected DataSourceConfig initDataSourceConfig(AutoGenerator autoGenerator) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://10.233.28.42:8306/cskb_1_0_work?useUnicode=true&characterEncoding=utf8");
        dataSourceConfig.setSchemaName("cskb_1_0_work");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456a?");
        autoGenerator.setDataSource(dataSourceConfig);
        return dataSourceConfig;
    }

    /**
     * @param autoGenerator
     * @return com.baomidou.mybatisplus.generator.config.PackageConfig
     * @throws
     * @title // 包配置
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/3 1:17 上午
     */
    private PackageConfig initPackageConfig(AutoGenerator autoGenerator) {

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("");
        packageConfig.setParent("com.kylinhunter.plat");
        packageConfig.setService("api.module." + module.getName() + ".service.local");
        packageConfig.setEntity("api.module." + module.getName() + ".bean.entity");
        packageConfig.setMapper("dao.mapper." + module.getName());
        packageConfig.setXml("dao.mapper." + module.getName());
        packageConfig.setServiceImpl(module.getName() + ".service.local");
        packageConfig.setController("gateway." + module.getName() + ".controller");
        autoGenerator.setPackageInfo(packageConfig);
        return packageConfig;

    }

    public TemplateConfig initTemplateConfig(AutoGenerator autoGenerator) {
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("/strategies/entity.java");
        // templateConfig.setService();
        // templateConfig.setTemplateController();
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);
        return templateConfig;

    }

    /**
     * @param autoGenerator
     * @return com.baomidou.mybatisplus.generator.config.StrategyConfig
     * @throws
     * @title 生成策略
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/3 1:36 上午
     */
    private StrategyConfig initStrategyConfig(AutoGenerator autoGenerator) {
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
        autoGenerator.setStrategy(strategyConfig);
        return strategyConfig;
    }

    private void initInjectionConfig(AutoGenerator autoGenerator) {

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        String templatePath = "/templates/mapper.xml.vm";
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();

        fileOutConfigs.add(new FileOutConfig(templatePath) {
            @SneakyThrows
            @Override
            public String outputFile(TableInfo tableInfo) {
                return UserDirUtils.getUserDirTmpResource().getAbsolutePath() + "/mapper/" + CodeGenerator.this.module
                        .getName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(fileOutConfigs);
        autoGenerator.setCfg(cfg);
    }

    public void clear(GlobalConfig globalConfig) {
        File outputDir = new File(globalConfig.getOutputDir());
        UserDirUtils.deleteQuietly(outputDir);
        log.info("delete outputDir=>" + outputDir.getAbsolutePath());
        UserDirUtils.deleteQuietly(UserDirUtils.getUserDirTmpResource());
        log.info("delete USER_DIR_TMP_RESOURCE=>" + UserDirUtils.getUserDirTmpResource().getAbsolutePath());

    }

    private void check() {
        if (module == null) {
            throw new ParamException("module is null");
        }
    }

    public void exec(boolean clear) {

        this.check();
        AutoGenerator autoGenerator = new AutoGenerator();
        GlobalConfig globalConfig = this.initGlobalConfig(autoGenerator);
        this.initDataSourceConfig(autoGenerator);
        this.initPackageConfig(autoGenerator);
        this.initStrategyConfig(autoGenerator);
        this.initTemplateConfig(autoGenerator);
        this.initInjectionConfig(autoGenerator);
        //        autoGenerator.setTemplateEngine(new VelocityTemplateEngine());
        if (clear) {
            this.clear(globalConfig);
        }
        autoGenerator.execute();

    }

    public Module createModule(String name) {
        this.module = new Module(name);
        return this.module;
    }

    /**
     * @description
     * @author BiJi'an
     * @date   2022-01-01 13:42
     **/

    @Data
    @Setter
    @Accessors(chain = true)
    public static class Module {
        private String name;
        private String[] tableNames;

        public Module(String name) {
            this.name = name;
        }

        public void setTableNames(String... tableNames) {
            this.tableNames = tableNames;
        }

    }

}
