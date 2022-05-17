package com.kylinhunter.plat.generator.web.pojo;

import lombok.Data;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-05 01:53
 **/
@Data
public class EntityField {
    private String name;    /*field名字*/
    private String typeFullName;  /*包名+类名*/
    private String typeSimpleName;  /*类名*/
    private String comment = "";
    private boolean primitive;
    private boolean datetime;
    private boolean supportCreate = true;
    private boolean supportUpdate = true;
    private boolean supportQuery = false;
    private boolean supportResponse = true;

}
