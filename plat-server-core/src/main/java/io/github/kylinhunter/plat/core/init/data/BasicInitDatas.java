package io.github.kylinhunter.plat.core.init.data;

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
public abstract class BasicInitDatas<C, D> implements InitDatas {

    private Map<String, C> initDatas = Maps.newHashMap();
    private Map<String, D> dbDatas = Maps.newHashMap();

    @Override
    public boolean canBeModified(String code) {
        if (initDatas.containsKey(code)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canBeDeleted(String code) {
        if (initDatas.containsKey(code)) {
            return false;
        }
        return true;
    }

    public void addInitData(String code, C c) {
        initDatas.put(code, c);

    }

    public void addDbData(String code, D d) {
        Objects.requireNonNull(d, "dbData is null");
        dbDatas.put(code, d);
    }

    public D getDbData(String code) {
        return dbDatas.get(code);
    }
}
