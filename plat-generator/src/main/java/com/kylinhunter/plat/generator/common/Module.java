package com.kylinhunter.plat.generator.common;

import java.util.List;

import com.google.common.collect.Lists;
import com.kylinhunter.plat.commons.util.ReflectionUtil;

import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 13:42
 **/

@Data
@Setter
@Accessors(chain = true)
public class Module {
    private String name;
    private List<String> tables = Lists.newArrayList();
    private List<String> entityClassNames = Lists.newArrayList();
    private List<Class<?>> entityClasses = Lists.newArrayList();

    public Module(String name) {
        this.name = name;
    }

    public String[] getTableArr() {
        return tables.toArray(new String[0]);
    }

    public Module addTable(String table) {
        this.tables.add(table);
        return this;
    }

    public void addEntityClass(Class<?> clazz) {
        this.entityClasses.add(clazz);
    }

    public void loadEntityClasses() {
        entityClassNames.forEach(e -> {
            entityClasses.add(ReflectionUtil.loadClass(e));
        });
    }

}
