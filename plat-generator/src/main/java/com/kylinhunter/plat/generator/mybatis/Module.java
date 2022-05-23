package com.kylinhunter.plat.generator.mybatis;

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
    private String[] tableNames;

    public Module(String name) {
        this.name = name;
    }

    public void setTableNames(String... tableNames) {
        this.tableNames = tableNames;
    }

}
