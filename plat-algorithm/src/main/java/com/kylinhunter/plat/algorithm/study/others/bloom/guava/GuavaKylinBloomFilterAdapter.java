package com.kylinhunter.plat.algorithm.study.others.bloom.guava;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.kylinhunter.plat.algorithm.study.others.bloom.KylinBloomFilter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-31 19:34
 **/
@SuppressWarnings({"UnstableApiUsage", "unchecked"})
public class GuavaKylinBloomFilterAdapter<T> implements KylinBloomFilter<T> {

    public BloomFilter<T> bloomFilter;

    public GuavaKylinBloomFilterAdapter(int expectedInsertions, double fpp, Class<T> cls) {
        if (Integer.class.isAssignableFrom(cls)) {
            this.bloomFilter =
                    BloomFilter.create((Funnel<? super T>) Funnels.integerFunnel(), expectedInsertions, fpp);
        } else if (Long.class.isAssignableFrom(cls)) {
            this.bloomFilter =
                    BloomFilter.create((Funnel<? super T>) Funnels.longFunnel(), expectedInsertions, fpp);
        } else if (String.class.isAssignableFrom(cls)) {
            this.bloomFilter =
                    BloomFilter.create((Funnel<? super T>) Funnels.stringFunnel(StandardCharsets.UTF_8),
                            expectedInsertions, fpp);
        }

    }

    @Override
    public boolean mightContain(T object) {
        return bloomFilter.mightContain(object);
    }

    @Override
    public boolean put(T object) {
        return bloomFilter.put(object);
    }
}
