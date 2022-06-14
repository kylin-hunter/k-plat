package com.kylinhunter.plat.generator.common;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kylinhunter.plat.commons.classloader.KPlatClassLoaderUtil;
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
    private Map<String, String> mapperClassNames = Maps.newHashMap();
    private Map<Class<?>, Class<?>> mapperClasses = Maps.newHashMap();

    public Module(String name) {
        this.name = name;
    }

    public String[] getTableArr() {
        return tables.toArray(new String[0]);
    }

    public Module addTable(String table) {
        if (!this.tables.contains(table)) {
            this.tables.add(table);
        }
        return this;
    }

    public void loadClasses() {
        entityClassNames.forEach(e -> {

            entityClasses.add(KPlatClassLoaderUtil.loadClass(e));

            //            entityClasses.add(ReflectionUtil.loadClass(e));
        });
        mapperClassNames.forEach((k, v) -> {

            mapperClasses.put(KPlatClassLoaderUtil.loadClass(k), KPlatClassLoaderUtil.loadClass(v));

            mapperClasses.put(ReflectionUtil.loadClass(k), ReflectionUtil.loadClass(v));
        });

    }

}
