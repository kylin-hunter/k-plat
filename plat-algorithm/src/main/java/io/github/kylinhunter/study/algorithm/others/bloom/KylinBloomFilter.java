package io.github.kylinhunter.study.algorithm.others.bloom;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-31 13:55
 **/
@SuppressWarnings("UnusedReturnValue")
public interface KylinBloomFilter<T> {

    boolean mightContain(T object);

    boolean put(T object);

}
