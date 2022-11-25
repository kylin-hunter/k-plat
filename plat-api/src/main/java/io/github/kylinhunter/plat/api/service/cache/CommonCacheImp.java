package io.github.kylinhunter.plat.api.service.cache;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-01 16:37
 **/
public abstract class CommonCacheImp<Z> implements CommonCache<Z> {
    @Override
    public Z get(String id) {
        return null;
    }

    @Override
    public boolean remove(String id) {
        return false;
    }

    @Override
    public boolean exist(String id) {
        return false;
    }

    @Override
    public boolean removeExist(String id) {
        return false;
    }

    @Override
    public boolean removeAll() {
        return false;
    }
}
