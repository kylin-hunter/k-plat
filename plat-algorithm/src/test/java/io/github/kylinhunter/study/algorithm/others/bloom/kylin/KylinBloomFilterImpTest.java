package io.github.kylinhunter.study.algorithm.others.bloom.kylin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.kylinhunter.study.algorithm.others.bloom.KylinBloomFilter;

@SuppressWarnings("ALL")
class KylinBloomFilterImpTest {

    @Test
    void contains() {
        KylinBloomFilter<Integer> kylinBloomFilter = new KylinBloomFilterImp(BloomType.N_1000000_01);

        for (int i = 0; i < 1000; i++) {

            kylinBloomFilter.put(i);
        }
        int num = 0;

        for (int i = 0; i < 1000; i++) {
            boolean ok = kylinBloomFilter.mightContain(i);
            if (ok) {
                num++;
            }
        }
        Assertions.assertEquals(1000, num);

    }
}