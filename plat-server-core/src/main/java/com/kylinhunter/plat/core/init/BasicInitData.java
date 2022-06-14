package com.kylinhunter.plat.core.init;

import java.util.Map;
import java.util.Objects;

import com.google.common.collect.Maps;

import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-15 01:33
 **/
@Getter
public abstract class BasicInitData<C, D> implements InitData {

    private Map<String, C> createDatas = Maps.newHashMap();
    private Map<String, D> dbDatas = Maps.newHashMap();

    @Override
    public boolean canBeModified(String code) {
        if (createDatas.containsKey(code)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canBeDeleted(String code) {
        if (createDatas.containsKey(code)) {
            return false;
        }
        return true;
    }

    protected void addCreateData(String code, C c) {
        createDatas.put(code, c);

    }

    protected void addDbData(String code, D d) {
        Objects.requireNonNull(d, "dbData is null");
        dbDatas.put(code, d);
    }

    public D getDbData(String code) {
        return dbDatas.get(code);
    }
}
