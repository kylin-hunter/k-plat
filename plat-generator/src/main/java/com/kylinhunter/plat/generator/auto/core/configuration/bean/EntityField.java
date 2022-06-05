package com.kylinhunter.plat.generator.auto.core.configuration.bean;

import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:53
 **/
@Data
public class EntityField {
    private String name;    /*field名字*/
    private String className;  /*包名+类名*/
    private String classSimpleName;  /*类名*/
    private String comment = "";
    private boolean primitive;
    private boolean datetime;
    private boolean supportCreate = true;
    private boolean supportUpdate = true;
    private boolean supportQuery = false;
    private boolean supportResponse = true;

}
