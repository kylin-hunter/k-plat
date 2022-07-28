package com.kylinhunter.plat.algorithm.study.others.bloom;

import org.junit.jupiter.api.Test;

class BloomFilterTest {

    @Test
    void contains() {
        BloomFilter bloomFilter = new BloomFilter();

        for (int i = 0; i < 10000000; i++) {
            bloomFilter.add(i);
        }
        int conflict = 0;

        for (int i = 0; i < 10000000; i++) {

            boolean ok = bloomFilter.contains( i*10);
            if (ok) {
                conflict++;
            }
        }

        System.out.println("confict:" + conflict);
    }
}