package io.github.kylinhunter.plat.api.service.cache;

/**
 * CommonCache
 *
 * @author biji'an
 * @since 2022-01-01
 */
public interface CommonCache<Z> {
    Z get(String id);

    boolean exist(String id);

    boolean remove(String id);

    boolean removeExist(String id);

    boolean removeAll();

}
