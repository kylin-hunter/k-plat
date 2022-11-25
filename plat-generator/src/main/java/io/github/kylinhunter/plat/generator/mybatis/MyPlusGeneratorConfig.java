package io.github.kylinhunter.plat.generator.mybatis;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Lists;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.generator.common.GenConst;
import io.github.kylinhunter.plat.generator.common.Module;

import io.github.kylinhunter.commons.component.CF;
import io.github.kylinhunter.commons.name.NCStrategy;
import io.github.kylinhunter.commons.name.NameConvertors;
import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:09
 **/
@Data
public class MyPlusGeneratorConfig {

    private DataSourceConfig dataSourceConfig;
    private GlobalConfig globalConfig;
    private PackageConfig packageConfig;
    private StrategyConfig strategyConfig;
    private TemplateConfig templateConfig;
    private File output;
    private Module module;
    private final NameConvertors nameConvertors = CF.get(NameConvertors.class);

    public MyPlusGeneratorConfig(Module module) {
        this.setOutput(GenConst.DEFAULT_OUTPUT);
        this.setDataSourceConfig(initDataSourceConfig());
        this.setGlobalConfig(initGlobalConfig());
        this.setPackageConfig(initPackageConfig(module));
        this.setStrategyConfig(initStrategyConfig(module));
        this.setTemplateConfig(initTemplateConfig());
        this.module = module;

    }

    public void init() {
        List<String> entityClasses =
                this.module.getTables().stream().map(this::getEntityClass).collect(Collectors.toList());
        module.setEntityClassNames(entityClasses);

        Map<String, String> mapperClasses = this.module.getTables().stream()
                .collect(Collectors.toMap(this::getEntityClass, this::getMapperClass));
        module.setMapperClassNames(mapperClasses);
    }

    private String getEntityClass(String table) {
        String parentPkg = packageConfig.getParent();
        String entityPkg = packageConfig.getEntity();
        table = table.replace(module.getTablePrefix(), "");
        String entityName = nameConvertors.convert(NCStrategy.SNAKE_TO_CAMEL_UP_FIRST, table);
        return parentPkg + "." + entityPkg + "." + entityName;

    }

    private String getMapperClass(String table) {
        String parentPkg = packageConfig.getParent();
        String mapperPkg = packageConfig.getMapper();
        table = table.replace(module.getTablePrefix(), "");
        String entityName = nameConvertors.convert(NCStrategy.SNAKE_TO_CAMEL_UP_FIRST, table);
        return parentPkg + "." + mapperPkg + "." + entityName + "Mapper";
    }

    public GlobalConfig initGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(GenConst.DEFAULT_OUTPUT.getAbsolutePath());
        globalConfig.setAuthor("biji'an");
        globalConfig.setOpen(false);
        globalConfig.setServiceName("%sService");  // 设置Service接口生成名称
        globalConfig.setSwagger2(true); // 实体属性 Swagger2 注解
        globalConfig.setFileOverride(true);

        return globalConfig;
    }

    public DataSourceConfig initDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig
                .setUrl("jdbc:mysql://localhost:3306/kp?serverTimezone=Asia/Shanghai&useUnicode=true"
                        + "&allowPublicKeyRetrieval=true"
                        + "&characterEncoding=utf8&useSSL=false");
        dataSourceConfig.setSchemaName("kp");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        return dataSourceConfig;

    }

    private static PackageConfig initPackageConfig(Module module) {

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("");
        packageConfig.setParent("io.github.kylinhunter.plat");
        packageConfig.setService("api.module." + module.getName() + ".service.local");
        packageConfig.setEntity("api.module." + module.getName() + ".bean.entity");
        packageConfig.setMapper(module.getName() + ".dao.mapper");
        packageConfig.setServiceImpl(module.getName() + ".service.local");
        packageConfig.setController(module.getName() + ".controller");

        return packageConfig;

    }

    private static StrategyConfig initStrategyConfig(Module module) {
        StrategyConfig strategyConfig = new StrategyConfig();

        strategyConfig.setInclude(module.getTableArr());
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setSuperEntityClass(BaseEntity.class);
        strategyConfig.setSuperEntityColumns("id", "sys_tenant_id", "sys_auto_updated", "sys_created_user_id",
                "sys_created_user_name",
                "sys_created_time", "sys_update_user_id", "sys_update_user_name", "sys_update_time", "sys_delete_flag",
                "sys_op_lock");

        //        strategyConfig.setVersionFieldName("sys_op_lock");
        //        strategyConfig.setSuperControllerClass("io.github.kylinhunter.plat.****.BaseController");
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        //        strategyConfig.setLogicDeleteFieldName("sys_delete_flag"); // 逻辑删除(deleted表明)
        TableFill tableFillCreate = new TableFill("sys_created_time", FieldFill.INSERT); // 创建时自动填充策略user数据库表
        TableFill tableFillUpdate = new TableFill("sys_update_time", FieldFill.INSERT_UPDATE); // 修改时
        List<TableFill> tableFills = Lists.newArrayList(tableFillCreate, tableFillUpdate);
        strategyConfig.setTableFillList(tableFills);
        strategyConfig.setTablePrefix("kplat_");
        return strategyConfig;
    }

    public TemplateConfig initTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("/strategies/entity.java");
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setController(null);
        templateConfig.setXml(null);

        templateConfig.setMapper("mybatis/plus/templates/mapper.java.vm");

        return templateConfig;

    }
}
