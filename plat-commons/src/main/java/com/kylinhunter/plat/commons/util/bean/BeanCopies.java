package com.kylinhunter.plat.commons.util.bean;

import java.util.List;

import com.google.common.collect.Lists;
import com.kylinhunter.plat.commons.util.convertor.ClassCopy;
import com.kylinhunter.plat.commons.util.convertor.FieldCopy;

import lombok.Data;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-13 19:09
 **/
@Data
public class BeanCopies {
    private boolean fieldEmpty = true;
    List<FieldCopy> fieldCopies;
    ClassCopy classCopy;

    public void addFieldCopy(FieldCopy fieldCopy) {
        if (fieldCopies == null) {
            fieldCopies = Lists.newArrayList();
        }
        fieldCopies.add(fieldCopy);
        fieldEmpty = false;
    }

}
